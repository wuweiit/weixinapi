package org.marker.weixin;

import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4Image;
import org.marker.weixin.msg.Msg4Link;
import org.marker.weixin.msg.Msg4Location;
import org.marker.weixin.msg.Msg4Text;
import org.marker.weixin.msg.Msg4Video;
import org.marker.weixin.msg.Msg4Voice;

/**
 * 主要用于接收微信服务器消息的接口
 * @author marker
 * */
public interface HandleMessageListener {

	/**
	 * 收到文本消息
	 * @param msg 消息
	 */
 	void onTextMsg(Msg4Text msg);
	
	/**
	 * 收到图片消息
	 * @param msg 消息
	 */
	void onImageMsg(Msg4Image msg);
	
	/**
	 * 收到事件推送消息
	 * @param msg 消息
	 */
	void onEventMsg(Msg4Event msg);
	
	/**
	 * 收到链接消息
	 * @param msg 消息
	 */
	void onLinkMsg(Msg4Link msg);
	
	/**
	 * 收到地理位置消息
	 * @param msg 消息
	 */
	void onLocationMsg(Msg4Location msg);
	
	/**
	 * 语音识别消息
	 * @param msg 消息
	 */
	void onVoiceMsg(Msg4Voice msg);
	
	/**
	 * 错误消息
	 * @param errorCode 错误码
	 */
	void onErrorMsg(int errorCode);

	/**
	 * 视频消息
	 * @param msg 消息
	 */
	void onVideoMsg(Msg4Video msg);

}
