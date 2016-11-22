package com.cong.file.operation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目增量备份实现类
 */
public class Patch {
	private Log logger = LogFactory.getLog(Patch.class);
	private String patchFile = ""; //补丁文件,由eclipse svn plugin生成  
	private String projectPath = ""; //项目文件夹路径
	private String webContent = ""; //web应用文件夹名  
	private String desPath = "";//补丁文件包存放路径  
	private String version = "";//补丁版本号  
	private int totalOfSucc = 0; //统计复制成功的文件数量
	private int totalOfFail = 0; //统计复制失败的文件数量
	private List<String> failFileList = new ArrayList<String>();
	private List<String> successFileList = new ArrayList<String>();
	
	public Patch() {}
	
	public Patch(String patchFile, String projectPath
			, String webContent, String desPath
			, String version) {
		this.patchFile = patchFile;
		this.projectPath = projectPath;
		this.webContent = webContent;
		this.desPath = desPath;
		this.version = version;
	}
	
	/**
	 * 复制文件
	 * @param copyList 待复制的文件
	 * @param lastUpdateList 已复制的文件
	 * @return 返回以复制的文件列表
	 * @throws Exception
	 */
	public void copyFiles(List<String> copyList, List<String> lastUpdateList) {
		for(String fullFileName : copyList){  
			String srcFileName = projectPath + File.separator + fullFileName;
			String desFileName = desPath + File.separator + version + File.separator 
					+ fullFileName.replaceAll(webContent, "");
			copyFile(srcFileName, desFileName, lastUpdateList); 
		}
	}  
	
	/**
	 * 复制文件
	 * @param srcFile 源文件路径
	 * @param desFile 目标文件路径
	 * @throws Exception
	 */
	private void copyFile(String srcFilePath, String desFilePath
			, List<String> lastUpdateList) { 
		File srcFile = new File(srcFilePath);
		File desFile = new File(desFilePath);
		
		if (srcFile.isDirectory()) {
			for (File subFile : srcFile.listFiles()) {
				String desc = desFilePath + File.separator + subFile.getName();
				copyFile(subFile.getPath(), desc, lastUpdateList);
			}
		} else {
			desFilePath = desFilePath.replaceAll("/", "\\\\");
			File desDir = new File(desFilePath.substring(
					0, desFilePath.lastIndexOf("\\")));  
			if(!desDir.exists()){
				desDir.mkdirs(); 
			}
			BufferedInputStream inBuff = null;  
			BufferedOutputStream outBuff = null;
			try {  
				// 新建文件输入流并对它进行缓冲  
				if (!srcFile.getName().startsWith("#")) {
					inBuff = new BufferedInputStream(
							new FileInputStream(srcFile));  
					// 新建文件输出流并对它进行缓冲  
					if (desFile.exists()) {
						desFile.delete();
					}
					if (desFile.createNewFile()) {
						outBuff = new BufferedOutputStream(
								new FileOutputStream(desFile));  
						// 缓冲数组  
						byte[] b = new byte[1024 * 5];
						int len;  
						while ((len = inBuff.read(b)) != -1) {  
							outBuff.write(b, 0, len);  
						}
						// 刷新此缓冲的输出流  
						outBuff.flush();
						totalOfSucc++; //复制文件成功计数器加1
						srcFilePath = srcFilePath.replace(projectPath+File.separator, "");
						//lastUpdateList.add(srcFilePath);
						successFileList.add(srcFilePath);
					}
				}
			} catch (Exception e) {
				logger.error("复制文件失败", e);
				totalOfFail++; //复制文件失败计数器加1
				srcFilePath = srcFilePath.replace(projectPath+File.separator, "");
				failFileList.add(srcFilePath); //失败文件
			} finally {  
				// 关闭流  
				if (inBuff != null)
					try {
						inBuff.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
				if (outBuff != null)
					try {
						outBuff.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
			} 
		}
	}  
    
	/**
	 * 执行“去重”操作
	 * 
	 * @return 返回新的文件列表
	 */
	public List<String> removeDup() throws Exception {
		FileInputStream fis = null;
		BufferedReader dr = null;
		FileOutputStream fos = null;
		List<String> fileList = new ArrayList<String>();
		try {
			File f = new File(desPath + File.separator + version);
			//创建版本目录
			if(!f.exists()){  
				f.mkdirs();  
			}
			String[] patchFiles = patchFile.split(",");
			for (int i = 0; i < patchFiles.length; i++) {
				fis = new FileInputStream(patchFiles[i]);   
				dr = new BufferedReader(new InputStreamReader(fis));
				//生成去重后的文件
				String patchFileName = patchFiles[i].substring(
						patchFiles[i].lastIndexOf("\\") + 1, patchFiles[i].length());
				File newFile = new File(desPath + File.separator + version 
						+ "/" + patchFileName);
				if(!newFile.exists()){  
					newFile.createNewFile();  
				}
				fos = new FileOutputStream(newFile);
				String line;
				while((line=dr.readLine()) != null){
					line = line.replaceAll(" ", "");
					if (!line.startsWith("#") && !"".equals(line) 
							&& !fileList.contains(line)){
						fileList.add(line);
						fos.write(line.getBytes());
						fos.write("\r\n".getBytes());
					}
				}
			}
		} finally {
			// 关闭流  
			if (fis != null)  
				fis.close();  
			if (dr != null)  
				dr.close(); 
			if (fos != null)  
				fos.close(); 
		}
		return fileList;
	}

	public int getTotalOfSucc() {
		return totalOfSucc;
	}

	public void setTotalOfSucc(int totalOfSucc) {
		this.totalOfSucc = totalOfSucc;
	}

	public int getTotalOfFail() {
		return totalOfFail;
	}

	public void setTotalOfFail(int totalOfFail) {
		this.totalOfFail = totalOfFail;
	}
	
	public List<String> getFailFileList(){
		return failFileList;
	}

	public List<String> getSuccessFileList() {
		return successFileList;
	}

	public void setSuccessFileList(List<String> successFileList) {
		this.successFileList = successFileList;
	}
	
}
