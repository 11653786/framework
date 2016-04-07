package com.yt.util.yt.myutils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangsan
 * @version 1.0
 * @package com.yt.util.yt.myutils
 * @date 2016/4/7 0007 11:13
 * @descption: 疯狂的王麻子团队!
 */
public class ValidUtils {
    /**
     * 验证手机
     *
     * @param str
     * @return
     * @author yangtao
     * @version 2.0, 2016年1月26日
     * @see
     * @since com.dongrongonline 2.0
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     * @author yangtao
     * @version 2.0, 2016年1月26日
     * @see
     * @since com.dongrongonline 2.0
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 判断是不是图片
     *
     * @param picName
     * @return
     * @author yangtao
     * @version 2.0, 2016年1月27日
     * @see
     * @since com.dongrongonline 2.0
     */
    public static boolean isPic(String picName) {
        picName = getSuffixName(picName);
        String[] images = {".gif", ".jpeg", ".bmp", ".png", ".x-png",
                ".x-bmp", ".x-ms-bmp", ".jpg"};
        for (String str : images) {
            if (str.equalsIgnoreCase(picName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 随机生成6位数字
     *
     * @return
     * @author yangtao
     * @version 2.0, 2016年1月28日
     * @see
     * @since com.dongrongonline 2.0
     */
    public static String AutoCreatePass() {
        Random random = new Random();
        String password = "";
        int getNum;
        while (password.length() < 6) {
            getNum = Math.abs(random.nextInt()) % 10 + 48;// 产生数字0-9的随机数
            // getNum = Math.abs(rd.nextInt())%26 + 97;//产生字母a-z的随机数
            char num1 = (char) getNum;
            String dn = Character.toString(num1);
            password += dn;
        }
        return password;
    }


    /**
     * 获取后缀名称 比如.png
     *
     * @param fileName
     * @return
     * @author yangtao
     * @version 2.0, 2016年1月27日
     * @see
     * @since com.dongrongonline 2.0
     */
    public static String getSuffixName(String fileName) {
        int position = fileName.lastIndexOf(".");
        fileName = fileName.substring(position);
        return fileName;
    }


}
