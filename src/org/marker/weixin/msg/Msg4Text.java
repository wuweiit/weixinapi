package org.marker.weixin.msg;

import org.marker.weixin.WXXmlElementName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 

/**
 * 文本消息
 * @author marker
 * @date 2013-8-25 上午8:54:11
 * @version 1.0
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 */
public class Msg4Text extends Msg {

	// 文本消息内容
	private String content;
	//位0x0001被标志时，星标刚收到的消息。
	private String funcFlag;
	// 消息id，64位整型
	private String msgId;
	
	/**
	 * 默认构造
	 * 初始化head对象，主要由开发者调用
	 * */
	public Msg4Text() {
		this.head = new Msg4Head();
		this.head.setMsgType(Msg.MSG_TYPE_TEXT);//设置消息类型
	}


	/**
	 * 此构造由程序内部接收微信服务器消息调用
	 * */
	public Msg4Text(Msg4Head head) {
		this.head = head;
	}

	@Override
	public void write(Document document) {
		Element root = document.createElement(WXXmlElementName.ROOT);
		head.write(root, document);
		Element contentElement = document.createElement(WXXmlElementName.CONTENT);
		contentElement.setTextContent(this.content);
		Element funcFlagElement = document.createElement(WXXmlElementName.FUNC_FLAG);
		funcFlagElement.setTextContent(this.funcFlag);
		root.appendChild(contentElement);
		root.appendChild(funcFlagElement);
		document.appendChild(root);
	}

	@Override
	public void read(Document document) {
		this.content = document.getElementsByTagName(WXXmlElementName.CONTENT).item(0).getTextContent();
		this.msgId = document.getElementsByTagName(WXXmlElementName.MSG_ID).item(0).getTextContent();
	}
	
	
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
 

	public String getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(String funcFlag) {
		this.funcFlag = funcFlag;
	}
}
