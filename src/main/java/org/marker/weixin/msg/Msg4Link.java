package org.marker.weixin.msg;

import org.marker.weixin.WXXmlElementName;
import org.w3c.dom.Document;

/**
 * 链接消息
 * @author marker
 * */
public class Msg4Link extends Msg {

	//消息标题
	private String title;	 
	//消息描述
	private String description;	 
	//消息链接
	private String url;	 
	//消息id，64位整型
	private String msgId;
	
	
	/**
	 * 开发者调用创建实例
	 * */
	public Msg4Link() {
		this.head = new Msg4Head();
		this.head.setMsgType(Msg.MSG_TYPE_LINK);
	}
	
	/**
	 * 推送来的消息采用此构造
	 * @param head
	 */
	public Msg4Link(Msg4Head head) {
		this.head = head; 
	}

	@Override
	public void write(Document document) {
//		Element root = document.createElement(WXXmlElementName.ROOT);
//		head.write(root, document);
//		Element titleElement = document.createElement(WXXmlElementName.TITLE);
//		titleElement.setTextContent(this.getTitle()); 
//		Element descriptionElement = document.createElement(WXXmlElementName.DESCRITION);
//		descriptionElement.setTextContent(this.getDescription()); 
//		Element urlElement = document.createElement(WXXmlElementName.URL);
//		urlElement.setTextContent(this.getUrl()); 
//		root.appendChild(descriptionElement);
//		root.appendChild(descriptionElement);
//		root.appendChild(urlElement);
//		document.appendChild(root);
	}
	
	@Override
	public void read(Document document) {
		this.title = document.getElementsByTagName(WXXmlElementName.TITLE).item(0).getTextContent();
		this.description = document.getElementsByTagName(WXXmlElementName.DESCRITION).item(0).getTextContent();
		this.url = document.getElementsByTagName(WXXmlElementName.URL).item(0).getTextContent();
		this.msgId = document.getElementsByTagName(WXXmlElementName.MSG_ID).item(0).getTextContent();
	} 
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}
