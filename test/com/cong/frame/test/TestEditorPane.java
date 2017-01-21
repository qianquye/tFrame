package com.cong.frame.test;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TestEditorPane {

  public static void main(String[] args) {
      try {
          String filename = "E:\\data\\test.txt";
          URL url = (new File(filename)).toURI().toURL();
          
          JEditorPane editorPane = new JEditorPane(url);
          editorPane.setEditable(false);
      
          int width = 500;
          int height= 300;
          
          JFrame frame = new JFrame();
        //  frame.setLocale(new Locale);
          frame.getContentPane().add(editorPane, BorderLayout.CENTER);
          //JOptionPane.showMessageDialog(editorPane, editorPane.getText());
          frame.setSize(width, height);
          frame.setVisible(true);
      } catch (IOException e) {
        e.printStackTrace();
      }
  }
  
}
