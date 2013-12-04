package org.marker.weixin.msg;

import org.marker.weixin.WXXmlElementName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 视频消息
 * @author marker
 * @date 2013-8-25 上午8:54:03
 * @version 1.0
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 */
public class Msg4Video extends Msg{
	// 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String mediaId;
	// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	private String thumbMediaId;
	// 消息id，64位整型
	private String msgId;
 
	
	/**
	 * 开发者调用
	 * */
	public Msg4Video() {
		this.head = new Msg4Head();
		this.head.setMsgType(Msg.MSG_TYPE_VIDEO);
	}
	
	
	
	/**
	 * @param head
	 */
	public Msg4Video(Msg4Head head) {
		this.head = head;
	}



	@Override
	public void write(Document document) {
		Element root = document.createElement(WXXmlElementName.ROOT);
		head.write(root, document);
		Element videoElement = document.createElement(WXXmlElementName.VIDEO);
		Element mediaIdElement = document.createElement(WXXmlElementName.MEDIAID);
		Element thumbMediaIdElement = document.createElement(WXXmlElementName.THUMBMEDIAID);
		videoElement.appendChild(mediaIdElement);
		videoElement.appendChild(thumbMediaIdElement);
		root.appendChild(videoElement); 
		document.appendChild(root);
	}
	
	
	// 因为用户不能发送音乐消息给我们，因此没有实现
	@Override
	public void read(Document document) {
		this.mediaId = getElementContent(document, WXXmlElementName.MEDIAID);
		this.thumbMediaId = getElementContent(document, WXXmlElementName.THUMBMEDIAID);
		this.msgId = getElementContent(document, WXXmlElementName.MSG_ID);
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



	/**
	 * @return the thumbMediaId
	 */
	public String getThumbMediaId() {
		return thumbMediaId;
	}



	/**
	 * @param thumbMediaId the thumbMediaId to set
	 */
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}



	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}



	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	
}