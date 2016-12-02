package com.cong.frame.listener;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.cong.fream.container.CopyPanelContainer;

public class GotoActionListener extends BasicActionListener {
	
	private Container container ; 
	private JComboBox<String> comboBox; //路径
	private JTextField fileInputField;//内容存储文件路径 
	
	public GotoActionListener(Container container,JComboBox<String> comboBox,JTextField fileInputField){
		this.container = container;
		this.comboBox = comboBox;
		this.fileInputField = fileInputField;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent action) {
	   String command = action.getActionCommand();
	   if("package".equals(command)){
		   this.clear(container);
		   String path = (String)comboBox.getSelectedItem();
		   String patch = fileInputField.getText();
		   String targetPath = null;
		   if(patch != null){
			   patch = patch.replace("/", "\\");
			   targetPath = patch.substring(0,patch.lastIndexOf("\\"));
		   }
		   Map<String, String> param = new HashMap<String,String>();
           param.put("path", path);
           param.put("patch",patch);
           param.put("targetPath",targetPath);
           
		   container.revalidate(); //重新绘制
		   //获取打包版面的页面
		   CopyPanelContainer cpc = new CopyPanelContainer(container);
		   cpc.setParam(param);
		   cpc.creator();
	   }
		
	}

}
