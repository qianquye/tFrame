package com.cong.mainFrame;

import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class MainFrame {
  //获取显示器的宽度和高度，并设置为公有属性，使用者可据些计算画框的位置
	public static final int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
 //设置主画框的缺省宽度各缺省位置
	private static int width = screenWidth/3;
	private static int height = screenHeight/4;
	private static int startX = screenWidth/3;
	private static int startY = screenHeight/3;
	private static JFrame frame;
	private static JPanel contentPane;
	
    private MainFrame(){}
    
    //init方法
    public static void init(String title){
    	frame = new JFrame(title);
    	frame.setLocation(new Point(startX,startY));
    	contentPane = (JPanel)frame.getContentPane();
    	contentPane.setPreferredSize(new Dimension(width,height));
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void init(String title,int w,int h,int x,int y){
    	width = w;
    	height = h ;
    	startX = x;
    	startY = y;
    	init(title);
    }
   
    public static void init(String title,int w,int h,int x,int y,String lookAndFeel){
    	try{
    		if(lookAndFeel.equalsIgnoreCase("windows")){
    			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeed");
    		}else if(lookAndFeel.equalsIgnoreCase("system")){
    			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    		}else if(lookAndFeel.equalsIgnoreCase("mottif")){
    			UIManager.setLookAndFeel("com.sum.java.swing,plaf.motif.MotifLookAndFeel");
    		}else{
    			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    		}
    			
    	}catch(Exception e){
    		
    	}
    	width = w;
    	height = h;
    	startX = x;
    	startY = y;
    	init(title);
    }
    
    //使画框可见，
    public static void start(){
    	frame.pack();
    	frame.setVisible(true);
    }
    
    public static JPanel getContentPane(){
    	return contentPane;
    }
    
    public static JFrame getMainFrame(){
    	return frame;
    }
    
    
    
    
}
