package com.cong.frame.start;

import java.awt.Container;

import com.cong.frame.main.MainFrame;
import com.cong.frame.menu.MenuBar;
import com.cong.fream.container.SearchPanelContainer;

public class StartView {
   public static void main(String[] args){
	   MainFrame.init("工具");
	   StartView sv = new StartView();
	   sv.setContentPane();
	   MainFrame.start();
	   
   }
   
   public void setContentPane(){
	   Container container = MainFrame.getContentPane();
	   //创建菜单
	   MenuBar menuBar = new MenuBar(container,MainFrame.getMainFrame());
	   menuBar.creator();
	   //搜索容器
	  // SearchPanelContainer spc = new SearchPanelContainer(container);
	  // spc.creator();
	   
	   
	
   }
}
