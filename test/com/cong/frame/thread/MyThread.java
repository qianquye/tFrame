package com.cong.frame.thread;

import java.util.Collections;

import com.cong.mainFrame.*;;

public class MyThread extends Thread{
    @Override
	public void run(){
    MainFrame.init("tt");
	  MainFrame.start();
   }
    
   public static void main(String[] args) {
	 MyThread myThread = new MyThread();
	   myThread.start();
   }
   
   public static void sortTest(){
	   
	//   Collections.sort();
   }
  
}
