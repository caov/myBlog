package com.caovan.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class CommonUtils {
    
    public static String getMD5(String str) {
        // 生成一个MD5加密计算摘要
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");

            byte[] byteArray;
            byteArray = str.getBytes("UTF-8");
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
}
