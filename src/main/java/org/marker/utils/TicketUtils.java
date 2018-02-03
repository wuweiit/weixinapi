package org.marker.utils;
/**
 * Created by marker on 2018/2/3.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.marker.config.Config;
import org.marker.weixin.exception.WeixinException;

/**
 *
 * ticket 工具类
 * @author marker
 *  2018-02-03 下午10:00
 **/
public class TicketUtils {


    /** jsapi_ticke URL */
    public static final String URL_JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";


    /**
     * 当前获取的 jsapiTicket (不用每次获取)
     */
    private static String jsapiTicket;
    /**
     * 获取access_token的时间
     */
    private static long jsapiTicketGetTime;


    private TicketUtils(){ }


    /**
     * 获取access_token
     *
     * @param accessToken accessToken
     * @return String
     * @throws WeixinException WeixinException
     */
    public static String getJsapiTicket(String accessToken) throws WeixinException {
        if (null != jsapiTicket) { // 已经获取了access_token
            long currentTime = System.currentTimeMillis();
            if ((currentTime - jsapiTicketGetTime) < 7200000) { // 过期了  | jsapi_ticket有效期为7200秒
                return jsapiTicket;
            }
        }

        // 从服务端从新获取access_token
        String url = URL_JSAPI_TICKET + "?access_token=" + accessToken + "&type=jsapi";
        String json = HttpUtil.sendHttpsGET(url);
        jsapiTicketGetTime = System.currentTimeMillis();
        JSONObject obj = JSON.parseObject(json);
        jsapiTicket = obj.getString("ticket");
        if (null == jsapiTicket) { // 错误
            throw new WeixinException(json);
        }
        return jsapiTicket;

    }



}
