package org.marker.weixin.api;
/**
 * Created by marker on 2018/2/5.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.marker.utils.HttpUtil;
import org.marker.weixin.api.domain.QRCode;
import org.marker.weixin.exception.WeixinException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 卡券 工具类
 *
 * @author marker
 * 2018-02-05 上午10:04
 **/
public class CardCouponsUtils {

    /** 日志 */
    private static Logger logger = LoggerFactory.getLogger(CardCouponsUtils.class);

    /**
     * URL_CARD_CREATE
     */
    private static final String URL_CARD_CREATE = "https://api.weixin.qq.com/card/create";

    /**
     * URL_QRCODE_CREATE
     */
    private static final String URL_QRCODE_CREATE = "https://api.weixin.qq.com/card/qrcode/create";


    /**
     * 创建卡券
     * @param accessToken token
     * @param body body
     * @throws WeixinException WeixinException
     */
    public static String create(String accessToken, String body) throws WeixinException {
        String url = new StringBuilder(URL_CARD_CREATE).append("?").append("access_token=")
                .append(accessToken)
                .toString();

        String json = HttpUtil.sendHttpsPOST(url, body);
        JSONObject obj = JSON.parseObject(json);
        String cardId = obj.getString("card_id");
        if (null == cardId) { // 错误
            logger.error("{}", json);
            throw new WeixinException("创建卡券失败！result=" + json);
        }
        return cardId;

    }


    /**
     * 创建二维码
     * @param accessToken token
     * @param body body
     * @return
     * @throws  WeixinException WeixinException
     */
    public static QRCode createQrcode(String accessToken, String body) throws WeixinException {
        String url = new StringBuilder(URL_QRCODE_CREATE).append("?").append("access_token=")
                .append(accessToken)
                .toString();

        String json = HttpUtil.sendHttpsPOST(url, body);
        QRCode qrCode = JSON.parseObject(json, QRCode.class);
        if (null == qrCode) { // 错误
            logger.error("{}", json);
            throw new WeixinException(json);
        }
        return qrCode;
    }

}
