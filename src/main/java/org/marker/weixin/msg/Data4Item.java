package org.marker.weixin.msg;


/**
 * 图文消息对象
 * 为图文消息对象提供数据支持
 * @author marker
 * @date 2013-8-24 上午11:03:26
 * @version 1.0
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 */
public class Data4Item {

	// 图文消息标题
	private String title;	 
	
	// 图文消息描述
	private String description;	 
	
	// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。
	private String picUrl;	 
	
	// 点击图文消息跳转链接
	private String url;
	
	
	
	/**
	 * 默认构造方法
	 * */
	public Data4Item() { }


	/**
	 * 所以属性的构造方法 
	 * @param title 标题
	 * @param description 描述
	 * @param picUrl 图片地址
	 * @param url url地址
	 */
	public Data4Item(String title, String description, String picUrl, String url) {
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
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
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
}
