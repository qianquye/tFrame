package com.cong.frame.listener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField; 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.cong.common.utils.Utils;
import com.cong.file.operation.Patch;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.swing.filechooser.FileNameExtensionFilter;

public class CopyFileActionListener implements ActionListener {
	
	private Log logger = LogFactory.getLog(CopyFileActionListener.class);
	private JButton packButton;
	private JComboBox<String> comboBoxP; //路径输入选择框
	private JComboBox<String> comboBoxF; //输入路径
	private JComboBox<String> comboBoxB; //补丁路径
	private JTextField textField; //版本号
	private  JTextArea textArea ;//版本说明
	private Patch patch;
	
	public CopyFileActionListener(){}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		//复制文件到指定的版本目录下
		List<String> lastUpdateList = new ArrayList<String>();
		if("packCommand".equals(command)){//打包按钮操作
			packButton.setEnabled(false);
			try{
				this.getFilePath();
				if(patch != null){
					//去掉重复的路径
					List<String> fileList = patch.removeDup();
					patch.copyFiles(fileList, lastUpdateList);
					JOptionPane.showMessageDialog(null, "打包成功！已更新文件" + patch.getTotalOfSucc() + "个，失败更新文件" 
							+ patch.getTotalOfFail() + "个。");
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "打包失败：" + e.getMessage());
				logger.error("打包失败", e);
			}finally{
				packButton.setEnabled(true);
			}
		}else if("failMessage".equals(command)){//获取打包后失败数据操作
			textArea.setText("");
			if(patch !=null && patch.getFailFileList().size() > 0){
				int size = patch.getFailFileList().size();
				for(int i = 0  ; i < size; i++){
					textArea.append(patch.getFailFileList().get(i)+"\n");
				}
			}else{
				JOptionPane.showMessageDialog(null, "没有数据");
			}
		}else if("successMessage".equals(command)){//获取打包后成功数据操作
			textArea.setText("");		
			if(patch.getSuccessFileList().size() >0){
				//排序
				Collections.sort(patch.getSuccessFileList());
				int size = patch.getSuccessFileList().size();
				for(int i = 0  ; i < size; i++){
					textArea.append(patch.getSuccessFileList().get(i)+"\n");
				}
			}else{
				JOptionPane.showMessageDialog(null, "没有数据");
			}
		}else if("spbutton".equals(command)){//源路径选择操作
			JFileChooser fileChooser = new JFileChooser();
			String path = (String)comboBoxP.getSelectedItem()==null?"C:/":(String)comboBoxP.getSelectedItem();
			fileChooser.setCurrentDirectory(new File(path));
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setDialogTitle("请选择源路径...");
			fileChooser.showDialog(new JLabel(),"选择");
			File file = fileChooser.getSelectedFile();
			if(file != null && file.isDirectory()){
				comboBoxP.addItem(file.getAbsolutePath());
				comboBoxP.setSelectedItem(file.getAbsolutePath());
			}
		}else if("sobutton".equals(command)){//目标输出路径操作
			JFileChooser fileChooser = new JFileChooser();
			String path = (String)comboBoxF.getSelectedItem()==null?"C:/":(String)comboBoxF.getSelectedItem();
			fileChooser.setCurrentDirectory(new File(path));
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setDialogTitle("请选择源路径...");
			fileChooser.showDialog(new JLabel(),"选择");
			File file = fileChooser.getSelectedFile();
			if(file != null && file.isDirectory()){
				comboBoxF.addItem(file.getAbsolutePath());
				comboBoxF.setSelectedItem(file.getAbsolutePath());
			}
		}else if("sibutton".equals(command)){//补丁路径操作
			JFileChooser fileChooser = new JFileChooser();
			String path =(String)comboBoxB.getSelectedItem()==null?"C:/":(String)comboBoxB.getSelectedItem();
			fileChooser.setCurrentDirectory(new File(path));
			fileChooser.setMultiSelectionEnabled(true);
			//过滤只上传txt
			FileNameExtensionFilter filter = new FileNameExtensionFilter("文体文件","txt");
			fileChooser.setFileFilter(filter);
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setDialogTitle("请选择补丁文件...");
			fileChooser.showDialog(new JLabel(), "选择");
			File[] files = fileChooser.getSelectedFiles();
			String filePath = "";
			for(int i = 0; i<files.length; i++){
				filePath += files[i].getAbsolutePath();
				if(i != files.length -1){
					filePath +=",";
				}
			}
			comboBoxB.setSelectedItem(filePath);
		}
		
		
	}
	
	
	
	/**
	 * 获取路径  实例化Patch
	 * @throws Exception
	 */
	private void  getFilePath() throws Exception{		
		String sourcePath = (String)comboBoxP.getSelectedItem();
		String targetPath = (String) comboBoxF.getSelectedItem();
		String patchFile = (String)comboBoxB.getSelectedItem();
	    String versionNum = textField.getText();
	    String versionComment = textArea.getText();
	  
	    
	    if(Utils.isBank(sourcePath)){
	    	JOptionPane.showMessageDialog(null, "请输入源路径");
	    }else if(Utils.isBank(targetPath)){
	    	JOptionPane.showMessageDialog(null,"请输入目标路径");
	    }else if(Utils.isBank(patchFile)){
	    	JOptionPane.showMessageDialog(null,"请输入补丁路径");
	    }else{
	    	sourcePath = sourcePath.replaceAll("/", "\\");
	    	targetPath = targetPath.replaceAll("/","\\");
	    	patchFile = patchFile.replaceAll("/","\\");
	    	String webContent = "WebRoot";
	    	patch = new Patch(patchFile,sourcePath,webContent,targetPath,versionNum);
	    	
	    }
	}
	
	
	
	
	public void setPackButton(JButton packButton){
		this.packButton = packButton;
	}
	public void setComboBoxP(JComboBox comboBoxP){
		this.comboBoxP = comboBoxP;
	}
	public void setComboBoxF(JComboBox comboBoxF){
		this.comboBoxF = comboBoxF;
	}
	public void setComboBoxB(JComboBox comboBoxB){
		this.comboBoxB = comboBoxB;
	}
	public void setTextField(JTextField textField){
		this.textField = textField;
	}
	public void setTextArea(JTextArea textArea){
		this.textArea = textArea;
	}

	
	
}
