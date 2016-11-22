package com.cong.mainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class MainFrameTest extends JFrame {
  private JLabel label1;
  private JButton button1;
  private JTextField text1;
  private JComboBox box;
  private JMenuBar menuBar;
  private JSlider slider;
  private JSpinner spinner;
  private JToolBar toolBar;
  
  
  public static void main(String[] args){
	 MainFrameTest mf = new MainFrameTest();
	 
	 mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	 mf.newFrame();
	 
	 mf.setVisible(true);
  }
	public void newFrame(){
		this.setSize(300, 500);
		this.getContentPane().setLayout(null);
		this.add(this.getTextField());
		this.add(this.getButton());
		this.add(this.getLabel());
		this.add(this.getBox(),null);
		this.setJMenuBar(this.getMenu());
		this.add(this.getSlider(),null);
		this.add(this.getSpinner(),null);
		this.add(this.getToolBar(),null);
		this.setTitle("Hello");
	}
	public JToolBar getToolBar(){
		if(toolBar == null){
			toolBar = new JToolBar();
			toolBar.setBounds(103, 260,71,20);
			toolBar.setFloatable(true);
		}
		return toolBar;
	}
	public JSpinner getSpinner(){
		if(spinner == null){
			spinner = new JSpinner();
			spinner.setBounds(103,220,80,20);
			spinner.setValue(100);
		}
		return spinner;
	}
	private JSlider getSlider(){
		if(slider == null){
			slider = new JSlider();
			slider.setBounds(103,200,100,20);
			slider.setMinimum(100);
			slider.setOrientation(0);
			slider.setValue(0);
		}
		return slider;
	}
	private JMenuBar getMenu(){
		if(menuBar == null){
			menuBar = new JMenuBar();
			JMenu m1 = new JMenu();
			m1.setText("文件");
			JMenu m2 = new JMenu();
			m2.setText("编辑");
			JMenu m3 = new JMenu(); 
			m3.setText("帮助");
			
			JMenuItem  item1 = new JMenuItem();
			item1.setText("打开");
			JMenuItem item2 = new JMenuItem();
			item2.setText("保存");
			JMenuItem item3 = new JMenuItem();
			item3.setText("退出");
			
			m1.add(item1);
			m1.add(item2);
			m1.add(item3);
			
		    menuBar.add(m1);
		    menuBar.add(m2);
		    menuBar.add(m3);
		}
		return menuBar;
	}
	
	private JComboBox getBox(){
		if(box == null){
			box = new JComboBox();
			box.setBounds(103, 140, 71, 27);
			box.addItem("1");
			box.addItem("2");
			box.addActionListener(new comboxListener());
		}
		return box;
	}
	
	private class comboxListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Object o = e.getSource();
			System.out.println(o.toString());
		}
	}
	
	private JLabel getLabel(){
		if(label1 == null){
			label1 = new JLabel();
			label1.setBounds(34, 49, 53, 18);
			label1.setText("Name");
			label1.setToolTipText("JLabel");
		}
		return label1;
	}
	
	private JButton getButton(){
		if(button1 == null){
			button1 = new JButton();
			button1.setBounds(103,110,71,27);
			button1.setText("OK");
			button1.setToolTipText("OK");
			button1.addActionListener(new HelloButton());
		}
		return button1;
	}
	
	private class HelloButton implements ActionListener{
	    public void actionPerformed(ActionEvent e){
	    	System.out.println("Hello world");
	    }
	}
	
	private JTextField getTextField(){
		if(text1 == null){
			text1 = new JTextField();
			text1.setBounds(96,49,160,20);
		}
		return text1;
	}
}

