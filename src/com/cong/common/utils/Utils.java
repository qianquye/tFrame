package com.cong.common.utils;

public class Utils {

	/**
	 * 判断字符串是否为空，空格也是
	 * @param content
	 * @return
	 */
	public static boolean isBank(CharSequence content){
		boolean isWhite = true;
		if(content == null)
			return isWhite;
		int length = content.length();
		for(int i = 0 ; i<length; i++){
			char _char = content.charAt(i);
			 if(!Character.isWhitespace(_char)){
				 isWhite = false;
				 break;
			 }
		}
		return isWhite;
	}
	
	public static void main(String[] args) {
		System.out.println(Utils.isBank("       "));
		System.out.println(Utils.isBank("    d   "));
		System.out.println(Utils.isBank("hiii  "));
		System.out.println(Utils.isBank("   hiii"));
	}
}
