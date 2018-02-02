package org.marker.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 这里是加密算法
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 * */
public class MySecurity {

	
	public static final String SHA_1 = "SHA-1";
	
	public static final String MD5 = "MD5";
 
	
	
	
	public String encode(String strSrc, String encodeType) {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = strSrc.getBytes();
		try {
			if (encodeType == null || "".equals(encodeType))
				encodeType = MD5;//默认使用MD5
			md = MessageDigest.getInstance(encodeType);
			md.update(bt);
			strDes = bytes2Hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return strSrc;
		}
		return strDes;
	}

	public String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	public static void main(String[] args) {
		MySecurity te = new MySecurity();
		String strSrc = "可以加密汉字";
		System.out.println("Source String:" + strSrc);
		System.out.println("Encrypted String:");
		System.out.println("Use MD5:" + te.encode(strSrc, null));
		System.out.println("Use MD5:" + te.encode(strSrc, "MD5"));
		System.out.println("Use SHA:" + te.encode(strSrc, "SHA-1"));
		System.out.println("Use SHA-256:" + te.encode(strSrc, "SHA-256"));
	}
}