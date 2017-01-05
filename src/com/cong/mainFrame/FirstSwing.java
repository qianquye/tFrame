package com.cong.mainFrame;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;


import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class FirstSwing {
  
	private static final int width = 341;
	private static final int height = 192;
	private static final int startX = 341;
	private static final int startY = 256;
	private JLabel label; 
	private int count = 0;
	
	public FirstSwing(){
		//创建主画框
		JFrame frame = new JFrame("欢迎进入GUI世界");
		//设置主画框的位置
		frame.setLocation(new Point(startX,startY));
		//获取主画框 的内容窗格，主画框中绘制的容器和组件都必须放在内家窗格中
		JPanel contentPane = (JPanel)frame.getContentPane();
		//设置内容窗格的首选大小，该大小同时 也会决定整个画面的大小
		contentPane.setPreferredSize(new Dimension(width,height));
		//创建一简单的标签放置在内容窗格中，标签上的文字放在标签中中央
		label = new JLabel("面向对象程序设计与java语言",JLabel.CENTER);
		//将标签放在内容窗格中央
		contentPane.add(label,BorderLayout.CENTER);
		//创建一按钮 放在上述的标签下方
		JButton button = new JButton("喜欢");
		//注册监听用户对按钮实施动作激发的事件处理都为内部类SimpleActionListener的对象实例
		button.addActionListener(new SimpleActionListener());
		//将按钮标签放在上述标签 的下方
		contentPane.add(button,BorderLayout.SOUTH);
		//设置在关闭主画框时的缺省行为为退出整个程序
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//将主画框整理成合适大小
		frame.pack();
		//使主画框可见，
		frame.setVisible(true);
	}
	
	private class SimpleActionListener implements ActionListener{
		public void actionPerformed(ActionEvent evt){
		    count = count + 1;
		    System.out.println("第"+count+"次按下");
		    label.setText("你喜欢面向对象java");
		}
	}
  
	public static void main(String[] args) {
		FirstSwing fs = new FirstSwing();
	}
}
