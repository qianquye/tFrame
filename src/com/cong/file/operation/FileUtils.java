package com.cong.file.operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


/**
 * @author zyongcong
 *
 * 2016-5-6
 */
public class FileUtils {
	
   private List<String> pathList ;
   private String proPath ;
   
   public static void main(String[] arge)throws Exception {
	   FileUtils fu = new FileUtils();
	  // List<String> list = new ArrayList<String>();
	  //-- fu.init("RadionboxRenderImpl", "E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWBSB\\.metadata\\.me_tcat\\webapps");
	   //list.add("RadionboxRenderImpl");
	  	//fu.getFilePath("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWBSB\\.metadata\\.me_tcat\\webapps",list);
	//--	System.out.println(fu.pathList);
	   //test searchbyCollect
	  /* List<String> nameList = new ArrayList<String>();
	   List<String> dataList = new ArrayList<String>();
	   nameList.add("file\\ZoomImageScale");
	   nameList.add("impl\\PreviewUploadFileRenderImpl");
       dataList.add("appPortal\\WEB-INF\\classes\\com\\eshore\\gov\\zhwb\\wbsb\\file\\ZoomImageScale.class");	   
       dataList.add("wbsbMgr\\WEB-INF\\classes\\com\\eshore\\gov\\zhwb\\wbsb\\file\\ZoomImageScale.class");	   
       dataList.add("ws\\WEB-INF\\classes\\com\\eshore\\gov\\wbsb\\ws\\action\\WbsbFileOperaAction.class");
       System.out.println(fu.searchbyCollect(nameList, dataList));
       */
	   String name="E:\\program\\applicatioon\\word\\zhwbWordDev\\data\\升版文件\\0204\\test.txt";
	   String path = "E:\\program\\applicatioon\\workspace\\myEclipse\\zhwbcode\\tyckMgr\\.metadata\\.me_tcat\\webapps";
	   fu.init(name, path, true);
	   System.out.println(fu.pathList.toString());
	}
	
   public FileUtils(){
	   pathList = new ArrayList<String>();
   }
   //初始化方法
   public void init(String fileNames,String filePath){
	   if(isBlank(fileNames))
		   return;
	   if(isBlank(filePath))
		   return;
	   List<String> list = new ArrayList<String>();
	   String[] strArr = fileNames.split(",");
	   for(int i = 0 ; i < strArr.length; i++){
		   list.add(strArr[i]);
	   }
	   
	  //调用搜索方法
	   this.getFilePath(filePath, list);
   }
   
   /**
    * 初始化方法
    * @param name  文件名文件路径
    * @param searchPath  搜索路径 
    * @param isfile  是否是路径
    */
   public void init(String name,String searchPath,boolean isfile){
	   //pathList = new ArrayList<String>();
	   if(isBlank(name))
		   return;
	   if(isBlank(searchPath))
		   return;
	   if(!isfile){
		   init(name,searchPath);
	   }else{
		  //通过文本来查找
		   List<String> firstNameList = new ArrayList<String>();
		   List<String> secondNameList = new ArrayList<String>();
		   List<String> folderList = new ArrayList<String>();
		   readFileList(name,firstNameList,secondNameList,folderList);
		   getFilePath(searchPath,firstNameList);
		   searchbyCollect(secondNameList);
		   pathList.addAll(folderList);
		}
	   
   }
   
   //获取文件路径
   public  void getFilePath(String searchPath,List<String> list){  
	   proPath = searchPath;
	   File file =  new File(searchPath);
	   if(!file.exists()){
		   return ;
	   }
	   if(list != null || list.size()>0){
		   for(int i = 0 ; i<list.size() ; i++){
			   /*1、判断文件名是否有包含的文件
			   2、如果包含把文件绝对路径存入集合中file.getAbsolutePath()
			   3、判断文件路径是否是一个目录  
			   4、继续搜索*/
			   readFilePath(file,list.get(i));
		   }
	   }
   }
   
   
   /**
    * 读取文件路径，存到list集合中
    *@author zyongcong
    * 2016-5-6
    * @param file
    * @param name
    */
    private void readFilePath(File file , String name){ 
 	   File[] files = file.listFiles();
 	  for(File f : files){
 		 String fileName = f.getName();
 		  if(fileName.contains(name)){  //判断文件中是否包含该文件名
 			   //如果有 存入集合中
 			   // setPath(f.getAbsolutePath());
 			  String relativePath = f.getAbsolutePath().replace(proPath+File.separator, "");
 			  setPath(relativePath);
 			   System.out.println(f.getAbsolutePath());
 		   }
 		  if(f.isDirectory()){//如果是文件夹 继续查
 			  // System.out.println(f.getAbsolutePath());
 			   File tempFile = new File(f.getAbsolutePath());
 			   readFilePath(tempFile, name);
 		   }
 	  }
    }
    
    /**
     * 读取文件清单中的文件
     * @date 20170204
     * @param filepath
     */
   private void readFileList(String filepath,List<String> firstNameList,List<String> secondNameList,List<String> folderList){
	   
	   
	   if(filepath == null || firstNameList == null || secondNameList == null || folderList ==  null){
		   return;
	   }
	   File file = new File(filepath);
	   FileInputStream  fis = null;
	   BufferedReader br = null;
	  
	   try{
		   fis = new FileInputStream(file);
		   br = new BufferedReader(new InputStreamReader(fis));
		   String line = null;
		   while((line=br.readLine()) !=null){
			   if(isBlank(line)){
				   continue;
			   }
			   line = line.replaceAll(" ", "");
				line=line.replaceAll("/","\\\\");
		  
			  int end = line.lastIndexOf(".");
			  if(end != -1){
				//取文件名
				   String firstStr = line.substring(line.lastIndexOf("\\")+1, end);
				   //取倒数第二个文件夹名与文件名
				   int start = line.lastIndexOf("\\",line.lastIndexOf("\\",line.lastIndexOf("\\")-2)-2);
				   String secondStr = line.substring(start+1,end);
				   firstNameList.add(firstStr);
				   secondNameList.add(secondStr);
			  }else{
				  //这里要对文件的src或WebRoot进行替换
				  line = line.replace("src", "WEB-INF\\classes").replace("\\WebRoot", "");
				  folderList.add(line);
			  }
			   
		   }
		   
	   }catch(FileNotFoundException notfound){
		  notfound.printStackTrace();
	   }catch(IOException io){
		   io.printStackTrace();
	   }
   }
  
   
   /**
    * 从集合中搜索结果
    * @param nameList 搜索名集合
    * @param dataList 数据集合
    * @return
    */
	   public List<String> searchbyCollect(List<String> nameList,List<String> dataList){
	   	List<String> result = new ArrayList<String>();
	   	if(nameList == null){
	   		return null;
	   	}
	   	if(dataList == null || dataList.size()<=0){
	   		return null;
	   	}
	   	for(int i =0 ; i<nameList.size(); i++){
	   		String nameStr = nameList.get(i);
	   		for(int j = 0 ;j<dataList.size();j++){
	   			if(dataList.get(j).contains(nameStr)){
	   				result.add(dataList.get(j));
	   			}
	   		}
	   	}
	   	return result;
	   }
   
    /**
     * 从集合中搜索结果
     * @param nameList 搜索名集合
     * @return
     */
    private List<String> searchbyCollect(List<String> nameList){
    	List<String> result = new ArrayList<String>();
    	if(nameList == null){
    		return null;
    	}
    	if(pathList == null || pathList.size()<=0){
    		return null;
    	}
    	for(int i =0 ; i<nameList.size(); i++){
    		String nameStr = nameList.get(i);
    		for(int j = 0 ;j<pathList.size();j++){
    			if(pathList.get(j).contains(nameStr)){
    				result.add(pathList.get(j));
    			}
    		}
    	}
    	pathList.clear();
    	pathList = result;
    	return pathList;
    }
    
    

    
    /**
     * 导出文件路径
     *  @author zyongcong 2016-11-29
     * @param list  文件径列表
     * @param filePath  文件路径 
     * @param append  是否在当前文件中追加
     */
    public void write2File(List<String> list,String filePath,boolean append){
    	if(list ==null ) {
    		JOptionPane.showMessageDialog(null, "没有要输出的内容");
    		return ;
    	}
    	if(filePath == null ) {
    		JOptionPane.showMessageDialog(null, "请输入文件路径");
    		return;
    	}
    	FileOutputStream fos = null;
    	File file = null;
		 try{
			 file = new File(filePath);	
			 if(!file.exists()){
				 file.createNewFile();
			 }
			 if(!file.isFile()){
				 JOptionPane.showMessageDialog(null, "输入的不是一个文件路径");
				 return;
			 }
			 fos = new FileOutputStream(file,append);		 
			 for(int i = 0 ; i < list.size(); i++){
			   String pathStr = list.get(i);
			  // pathStr = pathStr.replace(projectPath+File.separator, "");
			   fos.write(pathStr.getBytes());
			   fos.write("\r\n".getBytes());
			 }
			 JOptionPane.showMessageDialog(null, "导出成功"+list.size()+"个文件");
		 }catch(FileNotFoundException notFound){
			notFound.printStackTrace();
		 }catch( IOException io){
		    io.printStackTrace();
		 }finally{
			 if(fos != null){
				 try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
		 }
    	
    }
//*************************把搜索到的文件 写到文件中*******************************************   
    public void readFile(String searchPath,String filePath) throws Exception{
 	   //读取文件 获取文件名
 	   List<String> list = readFile(filePath);
 	   System.out.println("readFile(String searchPath,String filePath):"+list);
 	   if(list != null &&list.size() > 0){
 		   getFilePath(searchPath,list);
 	   }
 	   //将文件路径写入文件中
 	   //TODO
 		 System.out.println(pathList); 
    }
   
    /**
     * 读到文件
     *@author zyongcong
     * 2016-5-10
     * @param filePath
     * @return
     */
    public List<String> readFile(String filePath){
 	   List<String> list = new ArrayList<String>();
 	   if(filePath == null)
 		   throw new NullPointerException("the file is null");
 	   File file = new File(filePath);
 	   FileInputStream fis = null;;
 	   InputStreamReader isr = null;
 	   BufferedReader  br = null;
 	   try {
 		  fis = new FileInputStream(file);
 		  isr = new InputStreamReader(fis);
 		  br = new BufferedReader(isr);
 		  while(br.readLine() !=  null){
 			   String message = br.readLine();
 			   if(!isBlank(message)){
 			     message = getFileName(message);
 			     list.add(message);
 			   }
 		   }
 		} catch (FileNotFoundException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}finally{
 			try {
 				if(br != null)
 				   fis.close();
 				if(isr != null)
 				   isr.close();
 				if(fis !=  null)
 				   fis.close();
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 		}
 		 return list;
 	   }
    
    /**
     * 获取路径中的文件名
     *@author zyongcong
     * 2016-5-10
     * @param namePath
     * @return
     */
    private String getFileName(String namePath){
 	   if(namePath == null)
 		   return null ;
 	   int startIndex = namePath.lastIndexOf("/");
 	   if(startIndex<0){
 		   startIndex = namePath.lastIndexOf("\\");
 	   }
 	   int lastIndex = namePath.lastIndexOf(".");
 	   String name = null;
 	 //  System.out.println(startIndex+"-"+lastIndex);
 	   if(lastIndex > 0){
 		   if(this.isContain(namePath.substring(lastIndex+1)))
 			   name = namePath.substring(startIndex+1);
 		   else
 		    name = namePath.substring(startIndex+1, lastIndex);
 	   }else{
 		   name = namePath.substring(startIndex+1);
 	   }
 	   //System.out.println(name);
 	   return name;
    }
       
 //****************************************************************************
   
 
  

   
   private boolean isContain(String name){
	   if(isBlank(name))
		   return false;
	   List<String> endList = new ArrayList();
	   endList.add(".js");
	   endList.add(".xml");
	   endList.add(".css");
	   endList.add(".jsp");
	   endList.add(".gif");
	   endList.add(".png");
      if(endList.contains(name))
    	  return true;
      return false;
   }
   
   /**
    * 判断字符串是否为空白
    * 注意：只含有空格的字符串为空白，应返回true
    * @param str 需要判断的字符窜
    * @return
    */
   private boolean isBlank(String str) {
       int strLen;
       if ((null == str) || (0 == str.length())) {
           return true;
       }
       strLen = str.length();
       for (int i = 0; i < strLen; i++) {
           // 判断是否出现不为空格的情况
           if (!Character.isWhitespace(str.charAt(i))) {
               return false;
           }
       }
       return true;
   }
   
   public List<String> getPathList() {
		return pathList;
	}

   public void setPath(String path){
	   this.pathList.add(path);
   }
	
   
}
