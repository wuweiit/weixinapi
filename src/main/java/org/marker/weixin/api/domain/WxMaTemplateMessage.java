package org.marker.weixin.api.domain;/**
 * Created by marker on 2019/11/18.
 */

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author marker
 *  2019-11-18 22:08
 **/
@Data
public class WxMaTemplateMessage {

    private static final long serialVersionUID = 5063374783759519418L;

    /**
     * 接收者（用户）的 openid.
     * <pre>
     * 参数：touser
     * 是否必填： 是
     * 描述： 接收者（用户）的 openid
     * </pre>
     */
    private String touser;

    /**
     * 所需下发的模板消息的id.
     * <pre>
     * 参数：template_id
     * 是否必填： 是
     * 描述： 所需下发的模板消息的id
     * </pre>
     */
    private String template_id;

    /**
     * 点击模板卡片后的跳转页面，仅限本小程序内的页面.
     * <pre>
     * 参数：page
     * 是否必填： 否
     * 描述： 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
     * </pre>
     */
    private String page;

    /**
     * 表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id.
     * <pre>
     * 参数：form_id
     * 是否必填： 是
     * 描述： 表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
     * </pre>
     */
    private String formId;

    /**
     * 模板内容，不填则下发空模板.
     * <pre>
     * 参数：data
     * 是否必填： 是
     * 描述： 模板内容，不填则下发空模板
     * </pre>
     */
    private JSONObject data;


    /**
     * 设置数据
     * @param data
     */
    public void setData(JSONObject data) {
        this.data = data;
    }



}
