package org.marker.weixin.api;/**
 * Created by marker on 2019/11/18.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.marker.utils.HttpUtil;
import org.marker.weixin.api.domain.UserInfo;
import org.marker.weixin.api.domain.WxMaTemplateData;
import org.marker.weixin.api.domain.WxMaTemplateInfo;
import org.marker.weixin.api.domain.WxMaTemplateMessage;
import org.marker.weixin.exception.WeixinException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author marker
 * @create 2019-11-18 21:41
 **/

public class WeixinApiUtils {


    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(WeixinApiUtils.class);

    /**
     * 获取用户OPENID集合
     */
    public static final String URL_USERLIST = "https://api.weixin.qq.com/cgi-bin/user/get";


    /**
     * 获取用户基本信息
     */
    public static final String URL_USERINFO = "https://api.weixin.qq.com/cgi-bin/user/info";

    /**
     * 发送模板消息
     */
    public static final String URL_SEND_TEMPLATE_MSG = "https://api.weixin.qq.com/cgi-bin/message/template/send";

   /**
     * 模板List
     */
    public static final String URL_TEMPLATE_LIST = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template";


    /**
     * 获取用户的openID列表，需要缓存
     *
     * @return List<String> openid集合
     * @author marker
     */
    public static List<String> getUserOpenIdList(String nextOpenid) throws WeixinException {
        if (nextOpenid == null) {
            nextOpenid = "";
        }

        String accessToken = AccessTokenApiUtils.getAccessToken();

        String url = new StringBuilder(URL_USERLIST).append("?").append("access_token=")
                .append(accessToken).append("&next_openid=").append(nextOpenid)
                .toString();

        String json = HttpUtil.sendHttpsGET(url);

        List<String> list = new ArrayList<>(0);

        JSONObject obj = JSON.parseObject(json);
        JSONObject data = obj.getJSONObject("data");
        if (data != null) {
            list = data.getJSONArray("openid").toJavaList(String.class);
        } else {
            throw new WeixinException(obj.toJSONString());
        }

        return list;
    }


    /**
     * 获取用户详情
     *
     * @param openid
     * @return UserInfo 用户信息
     * @author marker
     */
    public static UserInfo getUserInfo(String openid) throws WeixinException {
        if (openid == null) {
            throw new WeixinException("getUserInfo()  openid is not null!");
        }

        String accessToken = AccessTokenApiUtils.getAccessToken();

        String url = new StringBuilder(URL_USERINFO).append("?").append("access_token=")
                .append(accessToken).append("&openid=").append(openid).append("&lang=zh_CN")
                .toString();

        String json = HttpUtil.sendHttpsGET(url);

        JSONObject obj = JSON.parseObject(json);
        if (obj.containsKey("errcode")) {
            throw new WeixinException(obj.toJSONString());
        }

        return JSON.parseObject(json, UserInfo.class);
    }
    /**
     * 获取模板消息列表
     *
     * @return List<WxMaTemplateInfo> 模板集合
     * @author marker
     */
    public static List<WxMaTemplateInfo> getTemplateList() throws WeixinException {

        String accessToken = AccessTokenApiUtils.getAccessToken();

        String url = new StringBuilder(URL_TEMPLATE_LIST).append("?").append("access_token=")
                .append(accessToken)
                .toString();

        String json = HttpUtil.sendHttpsGET(url);

        JSONObject obj = JSON.parseObject(json);
        if (obj.containsKey("errcode")) {
            throw new WeixinException(obj.toJSONString());
        }

        JSONArray array = obj.getJSONArray("template_list");

        List<WxMaTemplateInfo> list = new ArrayList<>();
        Iterator it = array.iterator();
        while (it.hasNext()){
            JSONObject item = (JSONObject) it.next();
            list.add(item.toJavaObject(WxMaTemplateInfo.class));
        }
        return list;
    }


    /**
     * 发送模板消息
     *
     * @param openid     openid
     * @param templateId 模板Id
     * @param params     map参数
     * @return
     * @throws WeixinException
     */
    public static boolean sendTemplateMsg(String openid, String templateId, List<WxMaTemplateData> params) throws WeixinException {
        if (openid == null) {
            throw new WeixinException("getUserInfo()  openid is not null!");
        }

        String accessToken = AccessTokenApiUtils.getAccessToken();

        String url = new StringBuilder(URL_SEND_TEMPLATE_MSG).append("?").append("access_token=")
                .append(accessToken)
                .toString();


        WxMaTemplateMessage wxMaTemplateMessage = new WxMaTemplateMessage();

        wxMaTemplateMessage.setTouser(openid);
        wxMaTemplateMessage.setTemplate_id(templateId);

        JSONObject item = new JSONObject(params.size());
        for (WxMaTemplateData key : params) {

            JSONObject val = new JSONObject(2);
            val.put("color", key.getColor());
            val.put("value", key.getValue());
            item.put(key.getName(), val);
        }
        wxMaTemplateMessage.setData(item);


        String data = JSON.toJSONString(wxMaTemplateMessage);
        logger.debug("request data:\n{}\n", data);
        String json = HttpUtil.sendHttpsPOST(url, data);

        JSONObject obj = JSON.parseObject(json);
        if (obj.containsKey("errmsg") && "ok".equals(obj.getString("errmsg"))) {
            return true;
        } else {
            throw new WeixinException(obj.toJSONString());
        }
    }

}
