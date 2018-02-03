package org.marker.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author marker
 *
 */
public final class Sign {

    private Sign(){ }


    /**
     *
     * @param args 参数
     */
    public static void main(String[] args) {

        String jsapiTicket = "jsapi_ticket";

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://example.com";
        Map<String, String> ret = (Map<String, String>) sign(jsapiTicket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    };


    /**
     * 签名
     * @param jsapiTicket ticket
     * @param url url url地址
     * @return
     */
    public static JSON sign(String jsapiTicket, String url) {
        Map<String, Object> ret = new HashMap<String, Object>();
        String nonceStr = createNonceStr();
        String timestamp = createTimestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapiTicket +
                  "&noncestr=" + nonceStr +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapiTicket);
        ret.put("nonceStr", nonceStr);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return new JSONObject(ret);
    }


    /**
     * 转Hex
     * @param hash hash
     * @return
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }


    /**
     * 创建随机字符串
     * @return
     */
    private static String createNonceStr() {
        return UUID.randomUUID().toString();
    }


    /**
     * 创建TimeStamp
     * @return
     */
    private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
