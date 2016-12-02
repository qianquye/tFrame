package com.cong.fream.container;


import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JTextArea;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import com.cong.frame.listener.SearchFileActionListener;
import com.cong.mainFrame.MainFrame;

import javax.swing.JComboBox;

public class SearchPanelContainer extends BasicContainer {
 
  private JTextField inputField; //搜索内容文本
  private JButton searchButton; //搜索按钮
  private JButton selectButton; //路径选择按钮
  private Container container;//
  //二次搜索文本框与按钮
  private JTextField search2inputField;
  private JButton search2Button;
  
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
	  
	  //提示
	  JLabel label = new JLabel("路径：");
	  label.setBounds(20, 10, 40, 30);
	  basic.add(label);
	  //创建下接列表框
	  comboBox = new JComboBox<String>();
	  comboBox.setEditable(true);
	  comboBox.setBounds(60, 10, width-170, 30);
	  //TODO 从xml中查找路径 addItem(Object anObject)  
	  comboBox.addItem("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWB\\.metadata\\.me_tcat\\webapps");
	  comboBox.addItem("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWBYY\\.metadata\\.me_tcat\\webapps");
	  comboBox.addItem("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWBPH\\.metadata\\.me_tcat\\webapps");
	  comboBox.addItem("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWBSB\\.metadata\\.me_tcat\\webapps");
	  basic.add(comboBox);
	  //创建按钮
	  selectButton = new JButton("选择路径");
	  selectButton.setBounds(width-100, 10, 80, 30);
	  //动作命令
	  selectButton.setActionCommand("ssbutton"); //search-select-button
      basic.add(selectButton);
	  
      //创建提示标签
      label = new JLabel("名称：");
	  label.setBounds(20, 50, 40, 30);
	  basic.add(label);
      //创建输入文本标签
      inputField = new JTextField();
      inputField.setBounds(60,50, width-170, 30);
      basic.add(inputField);
      
      //创建搜索按钮
      searchButton = new JButton("搜索");
      searchButton.setBounds(width-100, 90, 80, 30);
      //调用动作命令
      searchButton.setActionCommand("ss");//single-search      
      basic.add(searchButton);
      
      //文件输出域
      label = new JLabel("文件输出");
      label.setBounds(20, 120, 80, 30);
      basic.add(label);      
      search2inputField = new JTextField();
      search2inputField.setBounds(200,120,350,25);
      basic.add(search2inputField);  
      search2Button = new JButton("搜索");
      search2Button.setBounds(560, 120, 80, 25);
      search2Button.setActionCommand("ss2");
      basic.add(search2Button);
      
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
      
      //为选择按钮添加监听 
      selectButton.addActionListener(new SearchFileActionListener(comboBox));
      //添加监听
      searchButton.addActionListener(new SearchFileActionListener(inputField,comboBox,textArea));
      //将内容输出的文件路径添加监听
      selectfileButton.addActionListener(new SearchFileActionListener(fileInpuptField));
     // 导出按钮监听
      exportButton.addActionListener(new SearchFileActionListener(fileInpuptField,checkBox));
     //二次搜索监听
      search2Button.addActionListener(new SearchFileActionListener(search2inputField,null,textArea));
      return basic;
  }
  
 
  

}
