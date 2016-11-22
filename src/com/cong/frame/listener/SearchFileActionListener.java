package com.cong.frame.listener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import com.cong.file.operation.FileUtils;
import java.util.List;
import java.io.File;

public class SearchFileActionListener implements ActionListener {
   
	private String  name;//搜索的名字
	private JTextArea output; //输出的地方
	private JTextField inputField;
	private JComboBox comboBox; 
	private FileUtils fu ;
	private String isOk;
	
	
	public SearchFileActionListener(){}
	public SearchFileActionListener(String name){
		this.name = name;
	}
	
	public SearchFileActionListener(JComboBox comboBox){
		this.comboBox = comboBox;
	}
	
	public SearchFileActionListener(JTextField inputField,JComboBox comboBox,JTextArea container){
		this.inputField = inputField;
		this.comboBox = comboBox;
		this.output = container;
		fu = new FileUtils();
	}
	
	public void actionPerformed(ActionEvent evt){
		String command = evt.getActionCommand();
		
		if("ss".equals(command)){//搜索按钮动作操作
			String value  = inputField.getText();
			String path = (String)comboBox.getSelectedItem();			
			fu.init(value, path);
			List<String> listPath = fu.getPathList();
			output.setText("");
			//调用查询文件接口
			System.out.println(listPath.size());
			if(listPath != null && listPath.size() > 0 ){
				for(int i = 0; i<listPath.size(); i++){
					output.append(listPath.get(i)+"\n");
				}
			}	
			output.append("\n"+"文件名为:["+value+"]\n查询路径:["+path+"]\n搜索完毕,共搜索到("+listPath.size()+")个文件");
		}else if("ssbutton".endsWith(command)){//选择搜索路径操作
			JFileChooser fileChooser = new JFileChooser();
			String path = (String)comboBox.getSelectedItem();
			fileChooser.setCurrentDirectory(new File(path));
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setDialogTitle("请选择源路径...");
			fileChooser.showDialog(new JLabel(),"选择");
			File file = fileChooser.getSelectedFile();
			if(file != null && file.isDirectory()){
				comboBox.addItem(file.getAbsolutePath());
				comboBox.setSelectedItem(file.getAbsolutePath());
			}
		}
	}
}
