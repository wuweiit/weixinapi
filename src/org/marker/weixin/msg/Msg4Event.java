package org.marker.weixin.msg;

import org.marker.weixin.WXXmlElementName;
import org.w3c.dom.Document;

/**
 * 事件消息
 * 注意： 此消息只能是微信服务器推送过来
 * @author marker
 * @version 1.0
 * */
public class Msg4Event extends Msg {
	
	/** 订阅 */
	public static final String SUBSCRIBE = "subscribe";
	/** 取消订阅 */
	public static final String UNSUBSCRIBE = "unsubscribe";
	
	// 自定义菜单点击事件
	
	public static final String CLICK = "CLICK";
	public static final String SCAN = "scan";// 用户已关注时的事件推送
	public static final String LOCATION = "LOCATION";// 上报地理位置事件
	
	// 事件类型subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件)
	private String event;
	//事件KEY值，与自定义菜单接口中KEY值对应
	private String eventKey;
	// 二维码的ticket，可用来换取二维码图片
	private String ticket;
	
	private String latitude;//地理位置纬度
	private String longitude;//地理位置经度
	private String precision;//地理位置精度
	/**
	 * 程序内部调用
	 * */
	public Msg4Event(Msg4Head head) {
		this.head = head;
	}


	/**
	 * 因为此消息都是由微信服务器发送给我们的，我们不用发给微信服务器，因此不用实现write
	 * */
	@Override
	public void write(Document document) { }
	
	
	@Override
	public void read(Document document) {
		this.event = getElementContent(document, WXXmlElementName.EVENT);
		//用户未关注时，进行关注后的事件推送
		//用户已关注时的事件推送
		if(SUBSCRIBE.equals(this.event) || UNSUBSCRIBE.equals(this.event) || SCAN.equals(this.event)){
			this.eventKey = getElementContent(document, WXXmlElementName.EVENT_KEY);
			this.ticket = getElementContent(document, WXXmlElementName.TICKET);
		}else if(LOCATION.equals(this.event)){// 上报地理位置事件
			this.latitude = getElementContent(document, WXXmlElementName.LATITUDE);
			this.longitude = getElementContent(document, WXXmlElementName.LONGITUDE); 
			this.precision = getElementContent(document, WXXmlElementName.PRECISION);
		}else if(CLICK.equals(this.event)){// 自定义菜单事件
			this.eventKey = getElementContent(document, WXXmlElementName.EVENT_KEY);
		}
	}


	
	
	
	
	public String getEvent() {
		return event;
	}


	public void setEvent(String event) {
		this.event = event;
	}


	public String getEventKey() {
		return eventKey;
	}


	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}


	/**
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}


	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}


	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}


	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}


	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	/**
	 * @return the precision
	 */
	public String getPrecision() {
		return precision;
	}


	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	
	
	
	
}
