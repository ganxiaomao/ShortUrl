package com.youshusoft.util;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
	/** 
	 * BASE64字符串转为二进制数据
     * @param bytes 
     * @return 
     */  
    public static byte[] decode(String str) {  
        return Base64.decodeBase64(str.getBytes());  
    }  
  
    /** 
     * 二进制数据编码为BASE64字符串 
     * 
     * @param bytes 
     * @return 
     * @throws Exception 
     */  
    public static String encode(final byte[] bytes) {  
        return new String(Base64.encodeBase64(bytes));  
    }  
}
