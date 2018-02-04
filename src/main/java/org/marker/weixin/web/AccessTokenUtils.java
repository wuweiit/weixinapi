package org.marker.weixin.web;
/**
 * Created by marker on 2018/2/4.
 */

import com.alibaba.fastjson.JSON;
import org.marker.config.Config;
import org.marker.utils.HttpUtil;
import org.marker.weixin.exception.WeixinException;
import org.marker.weixin.web.domain.UserInfo;
import org.marker.weixin.web.domain.WebAccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Web能力获取的AccessToken
 * （目前的实现是针对单节点的，需要集群部署考虑使用redis缓存token）
 *
 * OAuth2.0
 *
 * @author marker
 *  2018-02-04 上午10:44
 **/
public final class AccessTokenUtils {

    /** 日志 */
    private static Logger logger = LoggerFactory.getLogger(AccessTokenUtils.class);

    /**
     * 获取tokenURL
     */
    public static final String URL_ACCESSTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * 当ACCESSTOKEN 失效时调用此接口
     */
    public static final String URL_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    /**
     * 用户信息
     */
    public static final String URL_USER_INFO = "https://api.weixin.qq.com/sns/userinfo";



    /**
     * 获取access_token的时间
     */
    private static long getTime;

    /**
     * 当前获取的access_token(不用每次获取)
     */
    private static WebAccessToken accessToken;



    /**
     * 获取access_token
     *
     * @param code 用户授权码（用户在网页授权后获取的）（声明周期：5分钟）
     * @return String
     * @throws WeixinException WeixinException
     */
    public static WebAccessToken getAccessToken(String code) throws WeixinException {
        if (null != accessToken) { // 已经获取了access_token
            long currentTime = System.currentTimeMillis();
            if ((currentTime - getTime) < 7200000) { // 过期了  | access_token有效期为7200秒
                return accessToken;
            }
        }

        // 从服务端从新获取access_token
        String url = URL_ACCESSTOKEN + "?appid=" + Config.APPID + "&secret=" + Config.SECRET + "&code=" + code + "&grant_type=authorization_code";

        String json = HttpUtil.sendHttpsGET(url);

        getTime = System.currentTimeMillis();
        accessToken= JSON.parseObject(json, WebAccessToken.class);
        if (null == accessToken) { // 错误
            logger.error("{}", json);
            throw new WeixinException(json);
        }
        return accessToken;
    }




    /**
     * 刷新access_token
     *
     * @param code 用户授权码（用户在网页授权后获取的）（声明周期：5分钟）
     * @return String
     * @throws WeixinException WeixinException
     */
    public static WebAccessToken refreshAccessToken(String code) throws WeixinException {
        if (null != accessToken) { // 已经获取了access_token
            long currentTime = System.currentTimeMillis();
            if ((currentTime - getTime) < 7200000) { // 过期了  | access_token有效期为7200秒
                return accessToken;
            }
        }

        // 从服务端从新获取access_token
        String url = URL_REFRESH_TOKEN + "?appid=" + Config.APPID + "&grant_type=refresh_token" + "&refresh_token=REFRESH_TOKEN";

        String json = HttpUtil.sendHttpsGET(url);

        getTime = System.currentTimeMillis();
        accessToken= JSON.parseObject(json, WebAccessToken.class);
        if (null == accessToken) { // 错误
            logger.error("{}", json);
            throw new WeixinException(json);
        }
        return accessToken;
    }


    /**
     * 获取用户信息
     *
     * @param accessToken token
     * @param openid openid
     * @param lang  zh_CN 简体，zh_TW 繁体，en 英语
     * @return
     */
    public static UserInfo getUserInfo(String accessToken, String openid, String lang) {
        String url = URL_USER_INFO + "?access_token=" + accessToken + "&openid=" + openid + "&lang=" + lang;
        String json = HttpUtil.sendHttpsGET(url);
        getTime = System.currentTimeMillis();
        return JSON.parseObject(json, UserInfo.class);
    }

    /**
     * 获取用户信息
     *
     * @param accessToken token
     * @param openid openid
     * @return
     */
    public static UserInfo getUserInfo(String accessToken, String openid) {
        return getUserInfo(accessToken, openid, "zh_CN");
    }







}
