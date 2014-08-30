package org.marker.weixin;


/**
 * 微信XML节点名称
 * (可能微信的消息格式更改，故采用接口配置形式来处理，以简便应万变)
 * @author marker
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 * */
public interface WXXmlElementName {

	/*       通用公共节点              */
	String ROOT = "xml";
	String TO_USER_NAME = "ToUserName";
	String FROM_USER_NAME = "FromUserName";
	String CREATE_TIME = "CreateTime";
	String MSG_TYPE = "MsgType";
	
	
	/* 消息ID */
	String MSG_ID = "MsgId";
	
	
	/*       文本消息节点              */
	String CONTENT = "Content";
	String FUNC_FLAG = "FuncFlag";
	
	
	
	String PIC_URL = "PicUrl";
	
	
	String TITLE = "Title";
	String DESCRITION = "Description";
	String URL = "Url";
	String MUSIC_URL = "MusicUrl";
	String HQ_MUSIC_URL = "HQMusicUrl";
	String MUSIC = "Music";
	
	/*       事件消息节点              */
	String EVENT = "Event";
	String EVENT_KEY = "EventKey";
	String TICKET = "Ticket";
	String LATITUDE = "Latitude";// 地理位置纬度
	String LONGITUDE = "Longitude";// 地理位置经度
	String PRECISION = "Precision";// 地理位置精度
	
	
	/* 地理位置消息 */
	String LOCATION_X = "Location_X";
	String LOCATION_Y = "Location_Y";
	String SCALE = "Scale";
	String LABEL = "Label";
	
	
	
	String ARTICLE_COUNT = "ArticleCount";
	
	String ARTICLES = "Articles";
	
	String ITEM = "item";
	
	/* 语音识别消息 */
	String MEDIAID = "MediaId";
	String FORMAT = "Format";
	String RECOGNITION = "Recognition";
	
	// 视频缩略图
	String THUMBMEDIAID = "thumbMediaId";
	
	
}
