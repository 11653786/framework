package com.yt.util.yt.myutils;

/**
 * Created by Administrator on 2016/2/26 0026.
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5加密工具类
 *
 * @author guanping
 * @version 2.0, 2016年1月26日
 * @since com.dongrongonline 2.0
 */
public class Md5Utils {
    /**
     * @param value
     * @return
     * @author guanping
     * @version 2.0, 2016年1月26日
     * @see
     * @since com.dongrongonline 2.0
     */
    public static String getMD5String(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] e = md.digest(value.getBytes());
            return toHexString(e);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return value;
        }
    }

    /**
     * @param bytes
     * @return
     * @author guanping
     * @version 2.0, 2016年1月26日
     * @see
     * @since com.dongrongonline 2.0
     */
    public static String getMD5String(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] e = md.digest(bytes);
            return toHexString(e);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @param bytes
     * @return
     * @author guanping
     * @version 2.0, 2016年1月26日
     * @see
     * @since com.dongrongonline 2.0
     */
    private static String toHexString(byte bytes[]) {
        StringBuilder hs = new StringBuilder();
        String stmp = "";
        for (int n = 0; n < bytes.length; n++) {
            stmp = Integer.toHexString(bytes[n] & 0xff);
            if (stmp.length() == 1)
                hs.append("0").append(stmp);
            else
                hs.append(stmp);
        }
        return hs.toString();
    }







    public static void main(String[] args) {
    }
}

