package com.cong.frame.listener;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.cong.frame.main.MainFrame;
import com.cong.fream.container.CopyPanelContainer;
import com.cong.fream.container.SearchPanelContainer;

public class MenuViewActionListener extends BasicActionListener{
	private Container container ; 
	
	public MenuViewActionListener(Container container){
		this.container = MainFrame.getContentPane();
	}
	

	public void actionPerformed(ActionEvent evt){
		String command = evt.getActionCommand();
		//获取container的组件 并清空
		this.clear(container);
		if("createfileBill".equals(command)){
			container.revalidate(); //重新绘制
			//获取生成版面的页面
			SearchPanelContainer spc = new SearchPanelContainer(container);
			spc.creator();	
		}else if("packageFile".equals(command)){
			container.revalidate(); //重新绘制
			//获取打包版面的页面
			CopyPanelContainer cpc = new CopyPanelContainer(container);
			cpc.creator();
		}else{
			//container.add(container);
		}
	}
	
	
}
