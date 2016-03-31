package com.yt.util.dhqjr;

import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

/**
 * 字节运算工具
 * date: 2015年7月24日 下午8:29:18 <br/>
 *
 * @author huangzhong
 * @version 2015年7月24日
 */
public class ByteUtil {
    private static Logger logger = Logger.getLogger(ByteUtil.class);

    // 用来将字节转换成 16 进制表示的字符
    private static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 找到字符在数组中的位置
     *
     * @param c
     * @return
     */
    private static int getIndex(char c) {
        int p = -1;
        for (int i = 0; i < hexDigits.length; i++) {
            if (hexDigits[i] == c) {
                p = i;
                break;
            }
        }
        return p;
    }

    /**
     * 将字节转化成字符串，转换算法如下:<br>
     * 1:每个字节长度为8位，分割为两个4位，高四位和低四位<br>
     * 2:将每个四位换算成16进制，并且对应ascii码，如0x01对应1,0x0d对应d,具体对应关系请见数组hexDigits[]<br>
     * 3:将得到的字符拼成字符串
     *
     * @param bytes
     * @return
     */

    public static String byteToChar(byte[] bytes) {
        // 每个字节用 16 进制表示的话，使用两个字符，所以字符数组长度是字节数字长度的2倍
        char str[] = new char[bytes.length * 2];
        // 表示转换结果中对应的字符位置
        int k = 0;
        // 每一个字节转换成 16 进制字符
        for (int i = 0; i < bytes.length; i++) {
            byte byte0 = bytes[i]; // 取第 i 个字节
            // 取字节中高 4 位(左边四位)的数字转换,>>>为逻辑右移，右移后，高四位变成低四位，需要对低四位之外的值进行消零运算
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            // 取字节中低 4 位(右边四位)的数字转换，并且和0xf进行"逻辑与"运算，以消除高位的值，得到纯净的低四位值
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }

    /**
     * 将字节转换成二进制数组，是byteToChar方法的逆运算，转换算法如下:<br>
     * 1:将字符按顺序每两个分为一组，分别找出每个字符在映射表hexDigits[]中的索引值，请见getIndex(char c)方法<br>
     * 2:每两个字符一组进行运算,将第一个字符的索引值逻辑左移四位，并和"0xf"进行"逻辑或"运算，目的是将低四位都设置为1，因为逻辑左移后，
     * 低四位都变成0了<br>
     * 3:将第二个字符的索引值和
     * "0xf0"进行"逻辑或"运算，目的的是将高位设置为1<br>
     * 4:将两个运算完的索引值进行"逻辑与"运算，得到了两个字符所代表的一个字节值<br>
     * 5:依次运算,最后得到字节数组,返回
     *
     * @param str
     * @return
     */

    public static byte[] charToByte(String str) {
        char[] chars = str.toCharArray();
        byte[] bytes = new byte[chars.length / 2];
        int k = 0;
        for (int i = 0; i < chars.length; i = i + 2) {
            // 得到索引值
            byte high = (byte) getIndex(chars[i]);
            byte low = (byte) getIndex(chars[i + 1]);
            // 第一个字符索引逻辑左移四位,并进行或运算,将低四位设置为1
            high = (byte) ((high << 4) | 0xf);
            // 第二个字符索引进行或运算,将高四位设置为1
            low = (byte) (low | 0xf0);
            // 两个字节进行与运算
            bytes[k++] = (byte) (high & low);
        }
        return bytes;
    }

    /**
     * MD5加密
     *
     * @param data
     * @return
     * @throws Exception
     */

    public static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest();
    }

    /**
     * 字符串转换成十六进制字符串
     */

    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }

    /**
     * 十六进制转换字符串
     */

    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * bytes转换成十六进制字符串
     */
    public static byte[] hexStr2Bytes(String src) {
        int m = 0, n = 0;
        int l = src.length() / 2;
        logger.info(l);
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
        }
        return ret;
    }

    private static byte uniteBytes(String src0, String src1) {
        byte b0 = Byte.decode("0x" + src0).byteValue();
        b0 = (byte) (b0 << 4);
        byte b1 = Byte.decode("0x" + src1).byteValue();
        return (byte) (b0 | b1);
    }

    /**
     * Convert hex string to byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }


    public static Object ByteToObject(byte[] bytes) {
        Object obj = null;
        try {
            // bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);
            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }


    public static byte[] ObjectToByte(java.lang.Object obj) {
        byte[] bytes = null;
        try {
            // object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
            bo.close();
            oo.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }


    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

}
