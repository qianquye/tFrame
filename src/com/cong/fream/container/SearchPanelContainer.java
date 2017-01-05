package com.cong.fream.container;


import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.cong.frame.listener.GotoActionListener;
import com.cong.frame.listener.SearchFileActionListener;
import com.cong.frame.listener.searchFileFocusListener;

public class SearchPanelContainer extends BasicContainer {
 
  private JTextField inputField; //搜索内容文本
  private JButton packageButton; //打包按钮
  private JButton selectButton; //路径选择按钮
  private Container container;//
  //二次搜索文本框
  private JTextField search2inputField;
  
  private JTextField fileInpuptField;//内容存储文件路径
  private JButton selectfileButton; //路径选择按钮
  private JCheckBox checkBox; //是否追加：指文件是新建还是在原来地方
  private JButton exportButton ; //导出文件按钮

  private JComboBox<String> comboBox; //文件路径的下拉列表框 
  
  
  public SearchPanelContainer(Container container){
	this.container = container; 
	
	
  }
  
  public void creator(){
	  this.container.add(createSearch(),BorderLayout.CENTER);
  }
 
  
  public JPanel createSearch(){
	  JPanel basic = new JPanel();
	  basic.setBounds(20, 10, width, height); 
	  basic.setLayout(null);
	  //跳到打包页面  - 调用动作命令package
	  packageButton = new JButton("打包");
	  packageButton.setBounds(width-100, 5, 80, 30);
      packageButton.setActionCommand("package");//     
      basic.add(packageButton);
      
	  //提示
	  JLabel label = new JLabel("路径：");
	  label.setBounds(20, 40, 40, 30);
	  basic.add(label);
	  //创建下接列表框
	  comboBox = new JComboBox<String>();
	  comboBox.setEditable(true);
	  comboBox.setBounds(60, 40, width-170, 30);
	  //TODO 从xml中查找路径 addItem(Object anObject)  
	  comboBox.addItem("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWB\\.metadata\\.me_tcat\\webapps");
	  comboBox.addItem("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWBYY\\.metadata\\.me_tcat\\webapps");
	  comboBox.addItem("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWBPH\\.metadata\\.me_tcat\\webapps");
	  comboBox.addItem("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWBSB\\.metadata\\.me_tcat\\webapps");
	  basic.add(comboBox);
	  //创建按钮
	  selectButton = new JButton("选择路径");
	  selectButton.setBounds(width-100, 40, 80, 30);
	  //动作命令
	  selectButton.setActionCommand("ssbutton"); //search-select-button
      basic.add(selectButton);
	  
      //创建提示标签
      label = new JLabel("名称：");
	  label.setBounds(20, 80, 40, 30);
	  basic.add(label);
      //创建输入文本标签
      inputField = new JTextField();
      inputField.setBounds(60,80, width-170, 30);
      basic.add(inputField);
      
    
      
      //文件输出域
      label = new JLabel("文件输出");
      label.setBounds(20, 120, 80, 30);
      basic.add(label);      
      search2inputField = new JTextField();

      search2inputField.setBounds(200,120,width-310,25);
      basic.add(search2inputField); 

      
      JScrollPane scrollPane =  (JScrollPane)makeTextPanel(null);
      scrollPane.setBounds(20, 150, width-50, height-150);
      basic.add(scrollPane);
      
      //将内容输出的文件路径
      label = new JLabel("文件输出：");
      label.setBounds(20, height+10, 80, 30);
      basic.add(label);  
      fileInpuptField = new JTextField();
      fileInpuptField.setBounds(80, height+10, width-320, 30);
      basic.add(fileInpuptField);
      //选择按钮
      selectfileButton = new JButton("选择路径");
      selectfileButton.setBounds(width-230, height+10, 80, 30);
	  //动作命令
      selectfileButton.setActionCommand("sfbutton"); //search-file-button
      basic.add(selectfileButton);
      //添加复选框
      checkBox = new JCheckBox();
      checkBox.setBounds(width-130, height+10, 30, 30);
      basic.add(checkBox);
      label = new JLabel("是否追加");
      label.setBounds(width-100,height+10,80, 30);
      basic.add(label); 
      
      //导出文件按钮
      exportButton = new JButton("导出");
      exportButton.setBounds(width-100, height+50, 80, 30);
      exportButton.setActionCommand("exportbutton");
      basic.add(exportButton);
      
      //失去焦点监听
      inputField.addFocusListener(new searchFileFocusListener(comboBox,textArea,"ss"));
      //失去焦点监听
      search2inputField.addFocusListener(new searchFileFocusListener(comboBox,textArea,"ss2") );
   
     
      //为选择按钮添加监听 
      selectButton.addActionListener(new SearchFileActionListener(comboBox));
      //将内容输出的文件路径添加监听
      selectfileButton.addActionListener(new SearchFileActionListener(fileInpuptField));
     // 导出按钮监听
      exportButton.addActionListener(new SearchFileActionListener(fileInpuptField,checkBox));

     //打包按钮监听
      packageButton.addActionListener(new GotoActionListener(container,comboBox,fileInpuptField));

      return basic;
  }
  
 
  

}
