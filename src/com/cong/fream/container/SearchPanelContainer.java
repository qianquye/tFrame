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

import com.cong.frame.listener.SearchFileActionListener;
import com.cong.mainFrame.MainFrame;

import javax.swing.JComboBox;

public class SearchPanelContainer extends BasicContainer {
 
  private JTextField inputField;
  private JButton searchButton;
  private JButton selectButton;
  private Container container;

  private JComboBox<String> comboBox;  
  
  
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
      
      
      label = new JLabel("文件输出");
      label.setBounds(20, 120, width, 30);
      basic.add(label);      
      JScrollPane scrollPane =  (JScrollPane)makeTextPanel(null);
      scrollPane.setBounds(20, 150, width-50, height-150);
      basic.add(scrollPane);
      
      //TODO 为选择按钮添加监听 selecteButton
      selectButton.addActionListener(new SearchFileActionListener(comboBox));
      //添加监听
      searchButton.addActionListener(new SearchFileActionListener(inputField,comboBox,textArea));
      
      return basic;
  }
  
 
  

}
