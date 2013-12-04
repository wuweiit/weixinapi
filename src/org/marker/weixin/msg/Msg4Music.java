package org.marker.weixin.msg;

import org.marker.weixin.WXXmlElementName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 音乐消息(只能回复)
 * @author marker
 * @date 2013-8-25 上午8:54:03
 * @version 1.0
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 */
public class Msg4Music extends Msg{
	// 标题
	private String title;
	// 描述
	private String description;
	// 音乐链接
	private String musicUrl;
	// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String hQMusicUrl;
	// 缩略图的媒体id，通过上传多媒体文件，得到的id
	private String thumbMediaId;
	
	
	/**
	 * 开发者调用
	 * */
	public Msg4Music() {
		this.head = new Msg4Head();
		this.head.setMsgType(Msg.MSG_TYPE_MUSIC);
	}
	
	
	
	@Override
	public void write(Document document) {
		Element root = document.createElement(WXXmlElementName.ROOT);
		head.write(root, document);
		Element musicElement = document.createElement(WXXmlElementName.MUSIC); 
		Element titleElement = document.createElement(WXXmlElementName.TITLE);
		titleElement.setTextContent(this.title);
		Element descriptionElement = document.createElement(WXXmlElementName.DESCRITION);
		descriptionElement.setTextContent(this.description);
		Element musicUrlElement = document.createElement(WXXmlElementName.MUSIC_URL);
		musicUrlElement.setTextContent(this.musicUrl);
		Element hQMusicUrlElement = document.createElement(WXXmlElementName.HQ_MUSIC_URL);
		hQMusicUrlElement.setTextContent(this.hQMusicUrl);
		Element thumbMediaIdElement = document.createElement(WXXmlElementName.THUMBMEDIAID);
		thumbMediaIdElement.setTextContent(this.thumbMediaId);

		musicElement.appendChild(titleElement);
		musicElement.appendChild(descriptionElement);
		musicElement.appendChild(musicUrlElement);
		musicElement.appendChild(hQMusicUrlElement);
		musicElement.appendChild(thumbMediaIdElement);
		root.appendChild(musicElement);
		
		document.appendChild(root);
	}
	
	
	// 因为用户不能发送音乐消息给我们，因此没有实现
	@Override
	public void read(Document document) { }
	
	
	
	
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
	public String getMusicUrl() {
		return musicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}
	public String getHQMusicUrl() {
		return hQMusicUrl;
	}
	public void setHQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = hQMusicUrl;
	}



	/**
	 * @return the hQMusicUrl
	 */
	public String gethQMusicUrl() {
		return hQMusicUrl;
	}



	/**
	 * @param hQMusicUrl the hQMusicUrl to set
	 */
	public void sethQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = hQMusicUrl;
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
	 
}
