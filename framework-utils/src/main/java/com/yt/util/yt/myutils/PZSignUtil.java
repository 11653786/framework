package com.yt.util.yt.myutils;

/**
 * Created by PUZE84 on 2016/11/18.
 */


import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

/**
 * 签名工具
 */
public class PZSignUtil {


    /**
     * 老网关加密方法
     *
     * @param md5Key
     * @param method
     * @param appKey
     * @param params
     * @return
     * @throws Exception
     */
    public static String signDataForOldForYK(String url, String method, String md5Key, Map<String, String> params) throws Exception {
        //1.设置请求参数
        Map<String, String> mapping = new HashMap<String, String>();
        mapping.put("lc", "0000000000000040");
        mapping.put("cc", "TG0001");
        mapping.put("cv", "IK3.7.00_Iphone");
        mapping.put("proto", "7");
        mapping.put("idfa", "00000000-0000-0000-0000-000000000000");
        mapping.put("idfv", "2D51A33E-AF58-4FCC-AD54-117E8793F3C0");
        mapping.put("devi", "d397ba078855b9b364d62e2d6800d0b6827cc6fd");
        mapping.put("osversion", "ios_10.000000");
        mapping.put("ua", "iPhone7_2");
        mapping.put("imei", "");
        mapping.put("imsi", "");
       // mapping.put("sid", "20ezp4ecfdDwwMcg49uW3jA18S59HHtZ826Gp6795lHJKxMMOy");
        mapping.put("conn", "wifi");
        mapping.put("mtid", "159fe58f8d47011813fb25bd91b46673");
        mapping.put("mtxid", "882593c08fed");
        mapping.put("s_sg", "b0db9fe22551cf8010a340fb0b653d52");
        mapping.put("s_sc", "100");
        mapping.put("s_sc", "1480070968");


        Iterator it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = params.get(key);
            mapping.put(key, value);
        }

        //2.计算签名

        //2.1 排序参数，组成参数串
        StringBuilder sb = new StringBuilder(128);
        String[] array = mapping.keySet().toArray(new String[mapping.size()]);
        if (array.length > 0) {
            Arrays.sort(array, StringComparator);
            for (String key : array) {
                sb.append(key);
                sb.append("=");
                sb.append(mapping.get(key));
            }
        }

        //2.2 按MD5算法计算签名
        String sig = Md5Utils.getMD5String(sb.append(md5Key).toString().getBytes("utf-8"));

        //2.3 将签名值加入到请求参数中
        mapping.put("sid", sig);

        //3. 拼接最终的请求URL，注意参数需要用URLEncode
        StringBuilder req = new StringBuilder();
        for (Map.Entry<String, String> entry : mapping.entrySet()) {
            req.append(entry.getKey()).append("=")
                    .append(URLEncoder.encode(entry.getValue(), "utf-8"))
                    .append("&");//进行url encoding
        }

        //4. 发送请求
        //http://puzeyf.f3322.net:8088/apigw/m.api?
        // String requestUrl = "http://192.168.6.15:8089/gw/request?" + req.toString();
        //  String requestUrl = "http://gw.puzeyf.com/m.api?" + req.toString();
        String requestUrl = url + method + req.toString();
        return requestUrl;
    }


    /**
     * 提供CPS方的订单查询前面方法<p/>
     * 方法返回get请求"？"后的内容，直接拿返回串加上url进行请求,只能进行单个参数传递的
     *
     * @param md5Key      MD5的秘钥
     * @param appKey      分配的appid
     * @param paramsKey   请求参数key
     * @param paramsValue 请求参数值
     * @return
     * @throws Exception
     */
    public static String signData(String md5Key, String method, String appKey, String paramsKey, String paramsValue) throws Exception {
        //1.设置请求参数
        Map<String, String> mapping = new HashMap<String, String>();

        mapping.put("_method", method);//方法名
        mapping.put("_key", appKey);//appKey
        mapping.put("_format", "json"); //设置返回结果json格式
        mapping.put("_signtype", "md5");//签名算法,目前支持md5
        // mapping.put("merchantId", merchantId);
        mapping.put(paramsKey, paramsValue);
        //2.计算签名

        //2.1 排序参数，组成参数串
        StringBuilder sb = new StringBuilder(128);
        String[] array = mapping.keySet().toArray(new String[mapping.size()]);
        if (array.length > 0) {
            Arrays.sort(array, StringComparator);
            for (String key : array) {
                sb.append(key);
                sb.append("=");
                sb.append(mapping.get(key));
            }
        }
        //2.2 按MD5算法计算签名
        String sig = encryptWithMd5(sb.append(md5Key).toString().getBytes("utf-8"));
        //2.3 将签名值加入到请求参数中
        mapping.put("_signmsg", sig);

        //3. 拼接最终的请求URL，注意参数需要用URLEncode
        StringBuilder req = new StringBuilder();
        for (Map.Entry<String, String> entry : mapping.entrySet()) {
            req.append(entry.getKey()).append("=")
                    .append(URLEncoder.encode(entry.getValue(), "utf-8"))
                    .append("&");//进行url encoding
        }

        //4. 发送请求
        String requestUrl = req.toString();

        return requestUrl;
    }

    /**
     * 提供CPS方的订单查询前面方法<p/>
     * 方法返回get请求"？"后的内容，直接拿返回串加上url进行请求,只能进行单个参数传递的
     *
     * @param md5Key MD5的秘钥
     * @param appKey 分配的appid
     * @return
     * @throws Exception
     */
    public static String signDataForMap(String md5Key, String method, String appKey, Map<String, String> params) throws Exception {
        //1.设置请求参数
        Map<String, String> mapping = new HashMap<String, String>();

        mapping.put("_method", method);//方法名
        mapping.put("_key", appKey);//appKey
        mapping.put("_format", "json"); //设置返回结果json格式
        mapping.put("_signtype", "md5");//签名算法,目前支持md5
        // mapping.put("merchantId", merchantId);
        Iterator it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = params.get(key);
            mapping.put(key, value);
        }

        //2.1 排序参数，组成参数串
        StringBuilder sb = new StringBuilder(128);
        String[] array = mapping.keySet().toArray(new String[mapping.size()]);
        if (array.length > 0) {
            Arrays.sort(array, StringComparator);
            for (String key : array) {
                sb.append(key);
                sb.append("=");
                sb.append(mapping.get(key));
            }
        }
        //2.2 按MD5算法计算签名
        String sig = encryptWithMd5(sb.append(md5Key).toString().getBytes("utf-8"));
        //2.3 将签名值加入到请求参数中
        mapping.put("_signmsg", sig);

        //3. 拼接最终的请求URL，注意参数需要用URLEncode
        StringBuilder req = new StringBuilder();
        for (Map.Entry<String, String> entry : mapping.entrySet()) {
            req.append(entry.getKey()).append("=")
                    .append(URLEncoder.encode(entry.getValue(), "utf-8"))
                    .append("&");//进行url encoding
        }

        //4. 发送请求
        String requestUrl = req.toString();

        return requestUrl;
    }


    /**
     * 老网关加密方法
     *
     * @param md5Key
     * @param method
     * @param appKey
     * @param params
     * @return
     * @throws Exception
     */
    public static String signDataForOld(String md5Key, String method, String appKey, Map<String, String> params) throws Exception {
        //1.设置请求参数
        Map<String, String> mapping = new HashMap<String, String>();

        mapping.put("_mt", method);//方法名
        mapping.put("_tpid", appKey);//appKey
        mapping.put("_ft", "json"); //设置返回结果json格式
        mapping.put("_sm", "md5");//签名算法,目前支持md5

        Iterator it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = params.get(key);
            mapping.put(key, value);
        }

        //2.计算签名

        //2.1 排序参数，组成参数串
        StringBuilder sb = new StringBuilder(128);
        String[] array = mapping.keySet().toArray(new String[mapping.size()]);
        if (array.length > 0) {
            Arrays.sort(array, StringComparator);
            for (String key : array) {
                sb.append(key);
                sb.append("=");
                sb.append(mapping.get(key));
            }
        }

        //2.2 按MD5算法计算签名
        String sig = Md5Utils.getMD5String(sb.append(md5Key).toString().getBytes("utf-8"));

        //2.3 将签名值加入到请求参数中
        mapping.put("_sig", sig);

        //3. 拼接最终的请求URL，注意参数需要用URLEncode
        StringBuilder req = new StringBuilder();
        for (Map.Entry<String, String> entry : mapping.entrySet()) {
            req.append(entry.getKey()).append("=")
                    .append(URLEncoder.encode(entry.getValue(), "utf-8"))
                    .append("&");//进行url encoding
        }

        //4. 发送请求
        //http://puzeyf.f3322.net:8088/apigw/m.api?
        // String requestUrl = "http://192.168.6.15:8089/gw/request?" + req.toString();
        //  String requestUrl = "http://gw.puzeyf.com/m.api?" + req.toString();
        String requestUrl = "http://puzeyf.f3322.net:8088/apigw/m.api?" + req.toString();
        return requestUrl;
    }


    private static final Comparator<String> StringComparator = new Comparator<String>() {
        public int compare(String s1, String s2) {
            int n1 = s1 == null ? 0 : s1.length();
            int n2 = s2 == null ? 0 : s2.length();
            int mn = n1 < n2 ? n1 : n2;
            for (int i = 0; i < mn; i++) {

                int k = s1.charAt(i) - s2.charAt(i);
                if (k != 0) {
                    return k;
                }
            }
            return n1 - n2;
        }
    };

    private static String encryptWithMd5(byte[] content) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return toHexString(md5.digest(content));
    }

    private static final char[] hexArray = "0123456789abcdef".toCharArray();

    private static final String toHexString(byte[] bs) {
        if (bs == null) return null;
        char[] hexChars = new char[bs.length * 2];
        for (int i = 0; i < bs.length; i++) {
            int v = bs[i] & 0xff;
            hexChars[i * 2] = hexArray[v >>> 4];
            hexChars[i * 2 + 1] = hexArray[v & 0x0f];
        }
        return new String(hexChars);
    }

    /**
     * 测试方法
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

//        Map<String, String> params = new HashMap<String, String>();
//        params.put("logid", "5");
//        params.put("id", "181920094");
//        params.put("uid", "110438312");
//
//        String request = signDataForOldForYK("http://116.211.167.106/", "api/user/info?", "20ezp4ecfdDwwMcg49uW3jA18S59HHtZ826Gp6795lHJKxMMOy", params);
//        String result = HttpUtil.getUrl(request);
//        System.out.println(result);

        Map<String, String> params = new HashMap<String, String>();
        params.put("language", "zh");
        params.put("extensions", "all");
        params.put("key", "743f496cbcb0fa4ed77dec485c50dfb9");
        params.put("location", "121.379681,31.233225");
        params.put("ts", "1480507813410");
        //http://restapi.amap.com/v3/geocode/regeo?language=zh&extensions=all&key=743f496cbcb0fa4ed77dec485c50dfb9&location=121.379681%2C31.233225&scode=51bb3b4615e18fced383ad5ca9eb6328&ts=1480507813410
        //scode=51bb3b4615e18fced383ad5ca9eb6328&ts=1480507813410


    }
}
