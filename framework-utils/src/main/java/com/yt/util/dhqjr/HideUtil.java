/**
 * Project Name:dronline-common-framework
 * File Name:HideUtil.java
 * Package Name:com.dronline.common.framework.base.utils
 * Date:2015年7月24日下午8:35:10
 * Copyright (c) 2015, DongrongOnline Tech, Inc. All Rights Reserved.
 *
 */

package com.yt.util.dhqjr;

import org.springframework.util.StringUtils;

/**
 * <p>
 * 隐藏字符串工具类
 * </p>
 * date: 2015年7月24日 下午8:35:32 <br/>
 *
 * @author huangzhong
 * @version 2015年7月24日
 */
public class HideUtil {

    private HideUtil() {
    }

    /**
     * 隐藏字符串中的某些字符
     *
     * @param str             需要隐藏的字符串
     * @param prefixShowCount 字符串中前段需要显示的字符个数
     * @param suffixShowCount 字符串中后段需要显示的字符个数
     * @return
     */
    public static String hide(String str, int prefixShowCount, int suffixShowCount) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (prefixShowCount < 0) {
            prefixShowCount = 0;
        }
        if (suffixShowCount < 0) {
            suffixShowCount = 0;
        }
        if (prefixShowCount >= str.length() || suffixShowCount >= str.length()) {
            return str;
        }
        char[] chs = str.toCharArray();
        for (int i = prefixShowCount, k = chs.length - suffixShowCount; i < k; i++) {
            chs[i] = '*';
        }
        return new String(chs);
    }

    /**
     * 隐藏绑卡编号
     *
     * @return
     */
    public static String hideCardNo(String cardNo) {
        if (cardNo == null || cardNo.length() == 0) {
            return cardNo;
        }
        if (cardNo.length() < 5) {
            return hide(cardNo, 0, 1);
        }
        if (cardNo.length() < 10) {
            return hide(cardNo, 3, 1);
        }
        return hide(cardNo, 6, 1);
    }

    /**
     * 隐藏姓名。姓名为两个字符时仅显示第一个字符，三个或三个以上字符时仅显示第一个和最后一个字符
     *
     * @param name
     * @return
     */
    public static String hideName(String name) {
        if (name == null || name.length() == 0) {
            return name;
        }
        if (name.length() < 3) {
            return hide(name, 1, 0);
        }
        return hide(name, 1, 1);
    }

    /**
     * 隐藏英文姓名。小于 8 个字符时仅显示前 3 个，否则前后各显示 3 个
     *
     * @param name
     * @return
     */
    public static String hideEnName(String name) {
        if (name == null || name.length() == 0) {
            return name;
        }
        if (name.length() < 8) {
            return hide(name, 3, 0);
        }
        return hide(name, 3, 3);
    }

    /**
     * 隐藏手机号码。仅显示手机号码中的前 3 个和后 3 个字符，其余使用 * 代替
     *
     * @param mobile
     * @return
     */
    public static String hideMobile(String mobile) {
        if (mobile == null || mobile.length() == 0) {
            return mobile;
        }
        return hide(mobile, 3, 3);
    }

    /**
     * 隐藏用户身证识别号码信息。身份证仅显示前 4 个字符和后 3 个字符，其他身证识别号码显示前 2 个和后 3 个字符
     *
     * @param cert
     * @return
     */
    public static String hideUserCert(String cert) {
        if (cert == null || cert.length() == 0) {
            return cert;
        }
        // 身份证
        if (cert.length() == 15 || cert.length() == 18) {
            return hide(cert, 4, 3);
        }
        // 护照、军官证、回乡证
        return hide(cert, 2, 3);
    }

    /**
     * 隐藏安全链接的参数，仅显示前 6 位和后 6 位
     *
     * @param str
     * @return
     */
    public static String hideSecurityLink(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return hide(str, 6, 6);
    }

    /**
     * 隐藏信用卡cvn号
     *
     * @param cvn
     * @return
     */
    public static String hideCvn(String cvn) {
        if (cvn == null || cvn.length() == 0) {
            return cvn;
        }
        if (cvn.length() == 3) {
            return hide(cvn, 1, 0);
        }
        return hide(cvn, 1, 1);
    }

    /**
     * 隐藏信用卡过期时间显示
     *
     * @param expireDate
     * @return
     */
    public static String hideExpireDate(String expireDate) {
        if (expireDate == null || expireDate.length() == 0) {
            return expireDate;
        }
        return hide(expireDate, 1, 1);
    }

    /**
     * 隐藏Email的显示 部分显示 第一位+@前一位+@及之后 例：m***e@gmail.com
     *
     * @param email
     * @return
     */
    public static String hideEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return email;
        } else {
            int index = email.indexOf("@");
            if (index < 0) {
                return email;
            }
            String sub1 = hide(email.substring(0, index), 1, 1);
            String sub2 = email.substring(index, email.length());
            email = sub1 + sub2;
        }
        return email;
    }
}
