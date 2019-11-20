package org.marker.weixin.exception;


/**
 * 微信调用异常
 *
 * @author marker
 */
public class WeixinException extends Exception {

	private static final long serialVersionUID = -2972908947741842813L;


    /**
     * 构造
     * @param msg 消息
     */
	public WeixinException(String msg) {
		super(msg); 
	}
}
