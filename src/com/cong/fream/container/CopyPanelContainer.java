package com.cong.fream.container;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.cong.frame.listener.CopyFileActionListener;

public class CopyPanelContainer extends BasicContainer{
	private Container container;
	private JComboBox<String> comboBoxP; //路径入选择框(源路径)
	private JComboBox<String> comboBoxF; //输入路径（目标路径）
	private JComboBox<String> comboBoxB; //输入补丁路径
	private JButton selectPButton;//路径选择按钮
	private JButton selectOButton;//输出文件按钮
	private JButton selectIButton;//输入的文件按钮
	private JTextField textField;
	
    
	public CopyPanelContainer(Container container){
		this.container = container;
		//container.remove(0);
		container.repaint();
		container.revalidate();//重新绘画
	}
	
	public void creator(){
		container.add(createCopy());
	}
	
	private JPanel createCopy(){
	  JPanel basic = new JPanel();
	  basic.setBounds(20, 10, width, height); 
	  basic.setLayout(null);
	  
	  //提示
	  JLabel label = new JLabel("源路径:");
	  label.setBounds(20, 10, 80, 30);
	  basic.add(label);
	  //创建下列表框
	  comboBoxP = new JComboBox<String>();
	  comboBoxP.setEditable(true);
	  comboBoxP.setBounds(100, 10, width-250, 30);
	  //TODO 从xml中查找路径 addItem(Object anObject)  
	  comboBoxP.addItem("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWB\\.metadata\\.me_tcat\\webapps");
	  comboBoxP.addItem("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWBYY\\.metadata\\.me_tcat\\webapps");
	  comboBoxP.addItem("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWBPH\\.metadata\\.me_tcat\\webapps");
	  comboBoxP.addItem("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWBSB\\.metadata\\.me_tcat\\webapps");

	  basic.add(comboBoxP);
	  //选择路径按钮
	  selectPButton = new JButton("选择路径");
	  selectPButton.setBounds(width-140,10,80,30);
	  //设置动作操作
	  selectPButton.setActionCommand("spbutton"); 
	  basic.add(selectPButton);
	  
	  //提示
	  label = new JLabel("目标路径:");
	  label.setBounds(20,50,80,30);
	  basic.add(label);
	  //创建下拉框
	  comboBoxF = new JComboBox<String>();
	  comboBoxF.setEditable(true);
	  comboBoxF.setBounds(100, 50, width-250, 30);
	  basic.add(comboBoxF);
	  //创建按钮
	  selectOButton = new JButton("选择路径");
	  selectOButton.setBounds(width-140,50,80,30);
	  selectOButton.setActionCommand("sobutton");
	  basic.add(selectOButton);
	  
	//提示
	  label = new JLabel("补丁路径:");
	  label.setBounds(20,90,80,30);
	  basic.add(label);
	  //创建下拉框
	  comboBoxB = new JComboBox<String>();
	  comboBoxB.setEditable(true);
	  comboBoxB.setBounds(100, 90, width-250, 30);
	  basic.add(comboBoxB);
	  //创建按钮
	  selectIButton = new JButton("选择路径");
	  selectIButton.setBounds(width-140,90,80,30);
	  selectIButton.setActionCommand("sibutton");
	  basic.add(selectIButton);
	  
	//提示
	  label = new JLabel("版本号:");
	  label.setBounds(20,130,80,30);
	  basic.add(label);
	  textField = new JTextField();
	  textField.setBounds(100, 130, width-250, 30);
	  basic.add(textField);
	  
	  label = new JLabel("版本说明");
	  label.setBounds(20,160,width,30);
	  basic.add(label);
	  JScrollPane scrollPane = (JScrollPane)makeTextPanel(null);
      scrollPane.setBounds(20,190,width-50,height-200);			  
	  basic.add(scrollPane);
	  
	  //添加日记查看按钮
	  JButton checkSuccMessage = new JButton("查看成功信息");
	  checkSuccMessage.setBounds(20,height, 150, 30);
	  checkSuccMessage.setActionCommand("successMessage");
	  JButton checkFailMessage = new JButton("查看失败信息");
	  checkFailMessage.setBounds(190,height, 150, 30);
	  checkFailMessage.setActionCommand("failMessage");
	  basic.add(checkSuccMessage);
	  basic.add(checkFailMessage);
	  
	 //添加按钮
	  JPanel temp = new JPanel();
	  temp.setBounds(20,height+50, width, 60);
	  //temp.setLayout(new FlowLayout());
	  JButton packButton = new JButton("打包");
	  packButton.setBounds((width/2-50),height,50,30);
	  packButton.setActionCommand("packCommand");
	  temp.add(packButton);
	  basic.add(temp);
	  
	  
	  //打包按钮监听
	  CopyFileActionListener copyFileListener = new CopyFileActionListener();
	  copyFileListener.setComboBoxB(comboBoxB);
	  copyFileListener.setComboBoxF(comboBoxF);
	  copyFileListener.setComboBoxP(comboBoxP);
	  copyFileListener.setTextField(textField);
	  copyFileListener.setTextArea(textArea);
	  copyFileListener.setPackButton(packButton);
	  
	  //TODO 添加监听
	  selectPButton.addActionListener(copyFileListener);
	  selectOButton.addActionListener(copyFileListener);
	  selectIButton.addActionListener(copyFileListener);
	  packButton.addActionListener(copyFileListener);
	  checkSuccMessage.addActionListener(copyFileListener);
	  checkFailMessage.addActionListener(copyFileListener);
	  
	  
	  return basic;
	}
	
	
}
