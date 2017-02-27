package com.cong.frame.listener;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.cong.common.utils.Utils;
import com.cong.file.operation.FileUtils;

public class searchFileFocusListener extends FocusAdapter{
    
	private String fieldSign ; //文件框标志
	private JTextArea output; //输出的地方
	private JComboBox<String> comboBox; //文本下拉框
	private FileUtils fu ;
	public static List<String> listPath = null;
	
	public searchFileFocusListener(JComboBox<String> comboBox,JTextArea output,String fieldSign){
		this.comboBox = comboBox;
		this.output = output;
		this.fieldSign = fieldSign;
		fu = new FileUtils();
	}
	
	public void focusLost(FocusEvent e){
		if("ss".equals(fieldSign)){ //第一次搜索的输入框
			//获取监听的组件
			JTextField textField = (JTextField)e.getSource();
			String value = textField.getText();
			if(Utils.isBank(value)) return;
			boolean isFile = isFile(value);
			String path = (String)comboBox.getSelectedItem();			
			if(listPath != null){
				listPath.clear(); 
			}
			fu.init(value, path,isFile);
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
		}else if("ss2".equals(fieldSign)){
			 JTextField textField = (JTextField)e.getSource();
			String value2 = textField.getText();
			if(Utils.isBank(value2)) return;
			
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

			
		}
	}
	
	private boolean isFile(String path){
		boolean flag = false;
		File file = new File(path);
		if(file.exists() && file.isFile()){
			flag = true;
		}
		return flag;
	}
}
