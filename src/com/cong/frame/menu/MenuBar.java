package com.cong.frame.menu;


import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import com.cong.frame.listener.MenuViewActionListener;


public class MenuBar {
	
	private Container container;
	private JFrame topFrame;
	private JTextArea spaceLabel;
	
	public MenuBar(Container container,JFrame topFrame){
		this.container = container;
		this.topFrame = topFrame;
	}
	//添加菜单项
	public JMenuBar createMenu(){
		//创建菜单
		 JMenuBar mBar = new JMenuBar();
	    //创建主菜单
		 JMenu fMenu = new JMenu("文件");
		 JMenu vMenu = new JMenu("视图");
		 mBar.add(fMenu);
		 mBar.add(vMenu);		 
		//创建子菜单 
		 JMenuItem projItem = new JMenuItem("项目更新");
		 
		 JMenuItem billItem = new JMenuItem("生成文件清单");
		 //添加按钮的动作命令，生成文件清单的监听
		 billItem.setActionCommand("createfileBill");
		 billItem.addActionListener(new MenuViewActionListener(container));
		 
		 JMenuItem packItem = new JMenuItem("打包");
		 //添加打包按钮的动作命令与打包的监听
		 packItem.setActionCommand("packageFile");
		 packItem.addActionListener(new MenuViewActionListener(container));
		 
		 vMenu.add(projItem);
		 vMenu.add(billItem);
		 vMenu.add(packItem);
		 
		return mBar;
	}

   public JScrollPane createSpace(){
	   spaceLabel = new JTextArea();
	   spaceLabel.setEditable(false);
	   JScrollPane scrollPane = new JScrollPane(spaceLabel);
	   return scrollPane;
   }
   
   public void creator(){
	   JMenuBar mBar = createMenu();
	   this.topFrame.add(mBar,BorderLayout.NORTH);
	   
   }
	
}
