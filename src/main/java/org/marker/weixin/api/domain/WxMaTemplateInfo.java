package org.marker.weixin.api.domain;
/**
 * Created by marker on 2019/11/19.
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @author marker
 *  2019-11-19 18:49
 **/
@Data
public class WxMaTemplateInfo implements Serializable {

    private String template_id; // ": "iPk5sOIt5X_flOVKn5GrTFpncEYTojx6ddbt8WYoV5s",
    private String title; // ": "领取奖金提醒",
    private String primary_industry; // ": "IT科技",
    private String deputy_industry; // ": "互联网|电子商务",
    private String content; // ": "{ {result.DATA} }\n\n领奖金额:{ {withdrawMoney.DATA} }\n领奖  时间:    { {withdrawTime.DATA} }\n银行信息:{ {cardInfo.DATA} }\n到账时间:  { {arrivedTime.DATA} }\n{ {remark.DATA} }",
    private String example; // ": "您已提交领奖申请\n\n领奖金额：xxxx元\n领奖时间：2013-10-10 12:22:22\n银行信息：xx银行(尾号xxxx)\n到账时间：预计xxxxxxx\n\n预计将于xxxx到达您的银行卡"



}
