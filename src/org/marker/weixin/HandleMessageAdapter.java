package org.marker.weixin;

import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4Image;
import org.marker.weixin.msg.Msg4Link;
import org.marker.weixin.msg.Msg4Location;
import org.marker.weixin.msg.Msg4Text;
import org.marker.weixin.msg.Msg4Video;
import org.marker.weixin.msg.Msg4Voice;



/**
 * 处理消息适配器(适配器模式)
 * @author marker
 * */
public class HandleMessageAdapter implements HandleMessageListener {

	public void onTextMsg(Msg4Text msg) {
		// TODO Auto-generated method stub
		
	}

	public void onImageMsg(Msg4Image msg) {
		// TODO Auto-generated method stub
		
	}

	public void onEventMsg(Msg4Event msg) {
		// TODO Auto-generated method stub
		
	}

	public void onLinkMsg(Msg4Link msg) {
		// TODO Auto-generated method stub
		
	}

	public void onLocationMsg(Msg4Location msg) {
		// TODO Auto-generated method stub
		
	}

	public void onErrorMsg(int errorCode) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.marker.weixin.HandleMessageListener#onVoiceMsg(org.marker.weixin.msg.Msg4Voice)
	 */
	public void onVoiceMsg(Msg4Voice msg) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.marker.weixin.HandleMessageListener#onVideoMsg(org.marker.weixin.msg.Msg4Video)
	 */
	public void onVideoMsg(Msg4Video msg) {
		// TODO Auto-generated method stub
		
	}

}
