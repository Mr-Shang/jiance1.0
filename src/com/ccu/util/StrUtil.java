package com.ccu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


/**
 * 字符串转化
 * @author CCU
 *
 */
public class StrUtil {

	
	/**
	 * liuzy 
	 * 将字符串转化为字节数组
	 */
	public static byte[] hexStrToBytes(String hexStr) {
		if(hexStr == null || hexStr.equals("")){
			return null;
		}
		hexStr = hexStr.toUpperCase();
		int len = hexStr.length() / 2;
		char hexChars[] = hexStr.toCharArray();
		byte[] bytes = new byte[len];
		for(int i = 0;i < len;i++) {
			int pos = i * 2;
			bytes[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return bytes;
	}
	
	private static int charToByte(char c) {
		// TODO Auto-generated method stub
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	/**
	 * liuzy
	 * 将字符串转化为整型数组
	 */
	
	public static int[] str2int(String bitStr) {
		int[] X = new int[8];
		char[] bitChar = bitStr.toCharArray();
		//System.out.println(bitChar[0]);
		int len = 8 - bitChar.length;
		//System.out.println(len);
		for(int i = 0;i < len;i++){
			X[i] = 0; 
		}
		for(int i = len;i < 8;i++){
			X[i] = bitChar[i - len] - 48;
		}
		
		return X;
	}
	
	/**
	 * 将16进制字符串转化为汉字（GB2312）
	 * @param src
	 * @return
	 */
	public static String newString(String src) {
    	 String finalStr = "";
		File f = new File(src);
	    try {
	      FileReader fs = new FileReader(f);
	      BufferedReader br = new BufferedReader(fs);
	      String text;
	      String newStr = "";
	      while ((text = br.readLine()) != null) {
	        System.out.println(text.replaceAll(" ", ""));
	        String[] str = text.split("20");
	        for(int k = 0; k < str.length; k++) {
	        	newStr = newStr + str[k];
	        }
	        System.out.println(newStr.replaceAll(" ", ""));
	        finalStr = new String(hexStrToBytes(newStr.replace(" ", "")), "gb2312");
	      }
	      br.close();
	    }
	    catch (Exception ex) {
	    }
	    return finalStr;
	}
	
	 /**
	  *liuzy 
	  *转化为16进制
	  */

	public static void printHexStr(byte[] bytes) {
		for(int i = 0;i < bytes.length ; i++){
		String hex = Integer.toHexString(bytes[i] & 0xFF);
			if(hex.length() == 1){
				hex = '0' + hex;
			}
		System.out.print(hex.toUpperCase() + ",");
		
		}
	}
}
