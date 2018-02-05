package org.marker.weixin.api;
/**
 * Created by marker on 2018/2/4.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.marker.config.Config;
import org.marker.utils.HttpUtil;
import org.marker.weixin.exception.WeixinException;
import org.marker.weixin.web.domain.UserInfo;
import org.marker.weixin.web.domain.WebAccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 在服务器端调用API的accessToken获取工具类
 *
 *
 * @author marker
 *  2018-02-05 上午10:00
 **/
public final class AccessTokenApiUtils {

    /** 日志 */
    private static Logger logger = LoggerFactory.getLogger(AccessTokenApiUtils.class);

    /**
     * 获取tokenURL
     */
    public static final String URL_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token";

    /** 获取access_token的时间 */
    private static long getTime;

    /** 当前获取的access_token(不用每次获取) */
    private static String accessToken;


    /**
     * 获取access_token
     *
     * @return String
     * @throws WeixinException WeixinException
     */
    public static String getAccessToken() throws WeixinException {
        if (null != accessToken) { // 已经获取了access_token
            long currentTime = System.currentTimeMillis();
            if ((currentTime - getTime) < 7200000) { // 过期了  | access_token有效期为7200秒
                return accessToken;
            }
        }
        // 从服务端从新获取access_token
        String url = new StringBuilder(URL_ACCESSTOKEN).append("?").append("grant_type=client_credential&appid=")
                .append(Config.APPID).append("&secret=").append(Config.SECRET)
                .toString();

        String json = HttpUtil.sendHttpsGET(url);
        getTime = System.currentTimeMillis();
        JSONObject obj = JSON.parseObject(json);
        accessToken = obj.getString("access_token");
        if (null == accessToken) { // 错误
            logger.error("{}", json);
            throw new WeixinException(json);
        }
        return accessToken;
    }








}
