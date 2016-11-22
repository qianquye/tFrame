package com.cong.file.operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zyongcong
 *
 * 2016-5-6
 */
public class FileUtils {
	
   private List<String> pathList ;
   
   public static void main(String[] arge)throws Exception {
	   FileUtils fu = new FileUtils();
	   List<String> list = new ArrayList<String>();
	   list.add("RadionboxRenderImpl");
//	   fu.getFilePath("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWBPH\\.metadata\\.me_tcat\\webapps",list);

//		fu.getFilePath("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWB\\.metadata\\.me_tcat\\webapps",list);
//     fu.getFilePath("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWBYY\\.metadata\\.me_tcat\\webapps",list);
//     fu.getFilePath("E:\\Primeton\\Platform\\apache-tomcat-5.5.20\\webapps",list);
	  	fu.getFilePath("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWBSB\\.metadata\\.me_tcat\\webapps",list);
		System.out.println(fu.pathList);
	 //  fu.readFile("E:\\sotfwear\\MyEclipse8.5_GOV\\ZHWB\\.metadata\\.me_tcat\\webapps", "E:\\data\\升版文件\\0510\\事项预览-样例预览.txt");
	}
	
   public FileUtils(){ }
   //初始化方法
   public void init(String fileNames,String filePath){
	   pathList = new ArrayList<String>();
	   if(isBlank(fileNames))
		   return;
	   if(isBlank(filePath))
		   return;
	   List<String> list = new ArrayList<String>();
	   String[] strArr = fileNames.split(",");
	   for(int i = 0 ; i < strArr.length; i++){
		   list.add(strArr[i]);
	   }
	   
	  //调用搜索方法
	   this.getFilePath(filePath, list);
   }
   
   //获取文件路径
   public  void getFilePath(String searchPath,List<String> list){   
	   File file =  new File(searchPath);
	   if(list != null || list.size()>0){
		   for(int i = 0 ; i<list.size() ; i++){
			   /*1、判断文件名是否有包含的文件
			   2、如果包含把文件绝对路径存入集合中file.getAbsolutePath()
			   3、判断文件路径是否是一个目录  
			   4、继续搜索*/
			   readFilePath(file,list.get(i));
		   }
	   }
   }
   
   
   /**
    * 读取文件路径，存到list集合中
    *@author zyongcong
    * 2016-5-6
    * @param file
    * @param name
    */
    private void readFilePath(File file , String name){ 
 	   File[] files = file.listFiles();
 	  for(File f : files){
 		 String fileName = f.getName();
 		  if(fileName.contains(name)){  //判断文件中是否包含该文件名
 			   //如果有 存入集合中
 			   setPath(f.getAbsolutePath());
 			   System.out.println(f.getAbsolutePath());
 		   }
 		  if(f.isDirectory()){//如果是文件夹 继续查
 			  // System.out.println(f.getAbsolutePath());
 			   File tempFile = new File(f.getAbsolutePath());
 			   readFilePath(tempFile, name);
 		   }
 	  }
    }
   
//*************************把搜索到的文件 写到文件中*******************************************   
    public void readFile(String searchPath,String filePath) throws Exception{
 	   //读取文件 获取文件名
 	   List<String> list = readFile(filePath);
 	   System.out.println("readFile(String searchPath,String filePath):"+list);
 	   if(list != null &&list.size() > 0){
 		   getFilePath(searchPath,list);
 	   }
 	   //将文件路径写入文件中
 	   //TODO
 		 System.out.println(pathList); 
    }
   
    /**
     * 读到文件
     *@author zyongcong
     * 2016-5-10
     * @param filePath
     * @return
     */
    public List<String> readFile(String filePath){
 	   List<String> list = new ArrayList<String>();
 	   if(filePath == null)
 		   throw new NullPointerException("the file is null");
 	   File file = new File(filePath);
 	   FileInputStream fis = null;;
 	   InputStreamReader isr = null;
 	   BufferedReader  br = null;
 	   try {
 		  fis = new FileInputStream(file);
 		  isr = new InputStreamReader(fis);
 		  br = new BufferedReader(isr);
 		  while(br.readLine() !=  null){
 			   String message = br.readLine();
 			   if(!isBlank(message)){
 			     message = getFileName(message);
 			     list.add(message);
 			   }
 		   }
 		} catch (FileNotFoundException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}finally{
 			try {
 				if(br != null)
 				   fis.close();
 				if(isr != null)
 				   isr.close();
 				if(fis !=  null)
 				   fis.close();
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 		}
 		 return list;
 	   }
    
    /**
     * 获取路径中的文件名
     *@author zyongcong
     * 2016-5-10
     * @param namePath
     * @return
     */
    private String getFileName(String namePath){
 	   if(namePath == null)
 		   return null ;
 	   int startIndex = namePath.lastIndexOf("/");
 	   if(startIndex<0){
 		   startIndex = namePath.lastIndexOf("\\");
 	   }
 	   int lastIndex = namePath.lastIndexOf(".");
 	   String name = null;
 	 //  System.out.println(startIndex+"-"+lastIndex);
 	   if(lastIndex > 0){
 		   if(this.isContain(namePath.substring(lastIndex+1)))
 			   name = namePath.substring(startIndex+1);
 		   else
 		    name = namePath.substring(startIndex+1, lastIndex);
 	   }else{
 		   name = namePath.substring(startIndex+1);
 	   }
 	   //System.out.println(name);
 	   return name;
    }
       
 //****************************************************************************
   
 
  

   
   private boolean isContain(String name){
	   if(isBlank(name))
		   return false;
	   List<String> endList = new ArrayList();
	   endList.add("js");
	   endList.add("xml");
	   endList.add("css");
	   endList.add("jsp");
	   endList.add("gif");
	   endList.add("png");
      if(endList.contains(name))
    	  return true;
      return false;
   }
   
   /**
    * 判断字符串是否为空白
    * 注意：只含有空格的字符串为空白，应返回true
    * @param str 需要判断的字符窜
    * @return
    */
   private boolean isBlank(String str) {
       int strLen;
       if ((null == str) || (0 == str.length())) {
           return true;
       }
       strLen = str.length();
       for (int i = 0; i < strLen; i++) {
           // 判断是否出现不为空格的情况
           if (!Character.isWhitespace(str.charAt(i))) {
               return false;
           }
       }
       return true;
   }
   
   public List<String> getPathList() {
		return pathList;
	}

   public void setPath(String path){
	   this.pathList.add(path);
   }
	
   
}
