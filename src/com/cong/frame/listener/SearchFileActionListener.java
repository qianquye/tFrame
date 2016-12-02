package com.cong.frame.listener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JCheckBox;

import com.cong.file.operation.FileUtils;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class SearchFileActionListener implements ActionListener {
   
	private String  name;//搜索的名字
	private JTextArea output; //输出的地方
	private JTextField inputField;//文本输入框
	private JComboBox<String> comboBox; //文本下拉框
	private FileUtils fu ;
//	private String isOk;
	private JCheckBox checkBox ; //复选框 
	
//	public List<String> listPath = searchFileFocusListener.listPath;
	
	
	public SearchFileActionListener(){}
	public SearchFileActionListener(String name){
		this.name = name;
	}
	
	public SearchFileActionListener(JComboBox<String> comboBox){
		this.comboBox = comboBox;
	}
	
	public SearchFileActionListener(JTextField inputField){
		this.inputField = inputField;
	   //	fu = new FileUtils();
	} 
	public SearchFileActionListener(JTextField inputField,JCheckBox checkBox){
		this.inputField = inputField;
		this.checkBox = checkBox;
		fu = new FileUtils();
	}
	
	public SearchFileActionListener(JTextField inputField,JComboBox<String> comboBox,JTextArea container){
		this.inputField = inputField;
		this.comboBox = comboBox;
		this.output = container;
		// fu = new FileUtils();
	}
	
	public void actionPerformed(ActionEvent evt){
		String command = evt.getActionCommand();
		List<String>  listPath = searchFileFocusListener.listPath;
		if("ss".equals(command)){//搜索按钮动作操作
			String value  = inputField.getText();
			String path = (String)comboBox.getSelectedItem();			
			fu.init(value, path);
			if(listPath != null){
				listPath.clear(); 
			}
			listPath = fu.getPathList();
			output.setText("");
			//调用查询文件接口
			System.out.println(listPath.size());
			if(listPath != null && listPath.size() > 0 ){
				for(int i = 0; i<listPath.size(); i++){
					output.append(listPath.get(i)+"\n");
				}
			}	
			output.append("\n"+"文件名为:["+value+"]\n查询路径:["+path+"]\n搜索完毕,共搜索到("+listPath.size()+")个文件");
		}else if("ss2".equals(command)){//从结果集中第二次搜索
			String value2  = inputField.getText();
			if(value2 == null){
				JOptionPane.showMessageDialog(null, "请输入搜索名称");
				return;
			}
			value2 = value2.replace("/", "\\");
			if(listPath == null){
				JOptionPane.showMessageDialog(null, "没有结果集！");
				return;
			}
			List<String> nameList = new ArrayList<String>();
			nameList.add(value2);
			List<String> temp = fu.searchbyCollect(nameList, listPath);
			if(temp != null){
				listPath.clear();
				listPath = temp;
			}
			//这个方法可以抽出来
			output.setText("");
			//调用查询文件接口
			System.out.println(listPath.size());
			if(listPath != null && listPath.size() > 0 ){
				for(int i = 0; i<listPath.size(); i++){
					output.append(listPath.get(i)+"\n");
				}
			}	
			output.append("\n"+"文件名为:["+value2+"]\n搜索完毕,共搜索到("+listPath.size()+")个文件");

			
		}else if("ssbutton".equals(command)){//选择搜索路径操作
			String path = (String)comboBox.getSelectedItem();
			File file = this.getFileChooser(path);
			
			if(file != null && file.isDirectory()){
				comboBox.addItem(file.getAbsolutePath());
				comboBox.setSelectedItem(file.getAbsolutePath());
			}
		}else if("sfbutton".equals(command)){//内容存放文件选择
			String path = inputField.getText()==null?"C:/":inputField.getText();
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(path));
			//过滤只上传txt
			FileNameExtensionFilter filter = new FileNameExtensionFilter("文体文件","txt");
			fileChooser.setFileFilter(filter);
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setDialogTitle("请选择补丁文件...");
			fileChooser.showDialog(new JLabel(), "选择");
			File file = fileChooser.getSelectedFile();
			if(file != null && !file.isDirectory()){
				inputField.setText(file.getAbsolutePath());
			}
		}else if("exportbutton".equals(command)){
			String exportPath = inputField.getText();
			if(exportPath == null){
				JOptionPane.showMessageDialog(null, "请选择导出路径");
				return;
			}
			if(listPath == null){
				JOptionPane.showMessageDialog(null, "没有要导出内容！");
				return;
			}
			boolean append = checkBox.isSelected();
			fu.write2File(listPath, exportPath, append);
		}
	}
	
	private File getFileChooser(String path){
		JFileChooser fileChooser = new JFileChooser();	
		fileChooser.setCurrentDirectory(new File(path));
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle("请选择源路径...");
		fileChooser.showDialog(new JLabel(),"选择");
		File file = fileChooser.getSelectedFile();
		return file;	
	}
	
	
}
