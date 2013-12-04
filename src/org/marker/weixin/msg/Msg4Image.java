package org.marker.weixin.msg;

import org.marker.weixin.WXXmlElementName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 图片消息
 * @author marker
 * @date 2013-8-25 上午8:53:37
 * @version 1.0
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 */
public class Msg4Image extends Msg{

	// 图片链接
	private String picUrl;
	// 消息id，64位整型
	private String msgId; 
	// 图片消息媒体id
	private String mediaId;
	
	
	/**
	 * 开发者调用
	 * */
	public Msg4Image() {
		this.head = new Msg4Head();
		this.head.setMsgType(Msg.MSG_TYPE_IMAGE);//设置消息类型
	}
	
	
	/**
	 * 程序内部调用
	 * */
	public Msg4Image(Msg4Head head) {
		this.head = head;
	}


	@Override
	public void write(Document document) {
		Element root = document.createElement(WXXmlElementName.ROOT);
		head.write(root, document);
		Element imageElement = document.createElement(WXXmlElementName.IMAGE); 
		Element mediaIdElement = document.createElement(WXXmlElementName.MEDIAID);
		imageElement.appendChild(mediaIdElement);
		root.appendChild(imageElement);
		document.appendChild(root);
	}
	
	
	@Override
	public void read(Document document) {
		this.picUrl = document.getElementsByTagName(WXXmlElementName.PIC_URL).item(0).getTextContent();
		this.mediaId = getElementContent(document, WXXmlElementName.MEDIAID);
		this.msgId   = document.getElementsByTagName(WXXmlElementName.MSG_ID).item(0).getTextContent();
	}


	public String getPicUrl() {
		return picUrl;
	}


	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}


	public String getMsgId() {
		return msgId;
	}


	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	
	/**
	 * @return the mediaId
	 */
	public String getMediaId() {
		return mediaId;
	}


	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	
	
}
