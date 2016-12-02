package com.cong.frame.listener;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;

public abstract class BasicActionListener implements ActionListener  {
	
	protected void clear(Container param){
		Component[]  c = param.getComponents();
		if(c != null && c.length > 1){
			for(int i = 1 ; i<c.length;i++){
				param.remove(c[i]);
			}
		}
	}
}
