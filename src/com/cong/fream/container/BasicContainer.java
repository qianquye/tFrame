package com.cong.fream.container;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.cong.mainFrame.MainFrame;

public class BasicContainer {
	
	  protected  int width = MainFrame.screenWidth*3/5;
	  protected int height = MainFrame.screenHeight*3/5;
	  protected int x =  MainFrame.screenWidth/5;
	  protected int y = MainFrame.screenHeight/5;
	  protected JTextArea textArea;
	  
	  
	  //创建滚动窗格的文本区域
	  protected Component makeTextPanel(String name ){
		 
		  textArea = new JTextArea(5,20);
		  textArea.setEditable(false);
		  JScrollPane scrollPane = new JScrollPane(textArea);
		  return scrollPane;
	  }
}
