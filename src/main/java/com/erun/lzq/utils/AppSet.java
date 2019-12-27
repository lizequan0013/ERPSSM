package com.erun.lzq.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppSet {
	// 是否打印调试信息
	public static boolean isDebug = true;
	
	public static String MD5(String src) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(src.getBytes());
			byte b[] = md5.digest();
			for (int offset = 0; offset < b.length; offset++) {
				int i;
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException nsae) {
			System.out.println("AppSet.MD5 throw NoSuchAlgorithmException");
		}
		return buf.toString().toUpperCase();
	}

}