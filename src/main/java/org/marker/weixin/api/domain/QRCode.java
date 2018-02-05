package org.marker.weixin.api.domain;
/**
 * Created by marker on 2018/2/5.
 */

import java.io.Serializable;

/**
 *
 * 二维码对象
 *
 * @author marker
 * 2018-02-05 上午10:20
 **/
public class QRCode implements Serializable{

    /**
     * 错误码
     */
    private String errcode; // ": 0,
    /**
     * 错误消息
     */
    private String errmsg; // ": "ok",
    /**
     * Ticket
     */
    private String ticket; // ": "gQF18DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAybGtJNEY2eUs4WFAxdWVBb05zNGsAAgQOsXdaAwSAM_EB",

    /**
     * 声明周期
     */
    private String expire_seconds; // ": 31536000,
    /**
     * URL
     */
    private String url; // ": "http://weixin.qq.com/q/02lkI4F6yK8XP1ueAoNs4k",
    /**
     * 二维码地址（Https）
     */
    private String show_qrcode_url; // ": "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQF18DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAybGtJNEY2eUs4WFAxdWVBb05zNGsAAgQOsXdaAwSAM_EB"




    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(String expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShow_qrcode_url() {
        return show_qrcode_url;
    }

    public void setShow_qrcode_url(String show_qrcode_url) {
        this.show_qrcode_url = show_qrcode_url;
    }
}
