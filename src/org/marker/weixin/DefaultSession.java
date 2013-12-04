package org.marker.weixin;

import java.util.ArrayList;
import java.util.List;

import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4Image;
import org.marker.weixin.msg.Msg4Link;
import org.marker.weixin.msg.Msg4Location;
import org.marker.weixin.msg.Msg4Text;
import org.marker.weixin.msg.Msg4Video;
import org.marker.weixin.msg.Msg4Voice;


/**
 * 默认的Session实现
 * 使用默认Session实现类需要通过添加HandleMessageListener监听器来对微信消息进行监听
 * 可以添加多个监听器来处理不同的消息内容。
 * 
 * 注意：此类并非线程安全，建议每次使用newInstance调用
 * @author marker
 * */
public class DefaultSession extends Session {
	
	/** 监听器集合 */
	private List<HandleMessageListener> listeners = new ArrayList<HandleMessageListener>(3);
	
	/**
	 * 私有构造方法
	 */
	private DefaultSession() {}
	
	
	/**
	 * 创建一个Session实例
	 * */
	public static DefaultSession newInstance() {
		return new DefaultSession();
	}
	
	
	/**
	 * 添加监听器
	 * @param handleMassge
	 */
	public void addOnHandleMessageListener(HandleMessageListener handleMassge){
		listeners.add(handleMassge);
	}
	
	
	/**
	 * 移除监听器
	 * */
	public void removeOnHandleMessageListener(HandleMessageListener handleMassge){
		listeners.remove(handleMassge);
	}


	@Override
	public void onTextMsg(Msg4Text msg) {
		for(HandleMessageListener currentListener : listeners){
			currentListener.onTextMsg(msg);
		}
	}

	@Override
	public void onImageMsg(Msg4Image msg) {
		for(HandleMessageListener currentListener : listeners){
			currentListener.onImageMsg(msg);
		}
	}

	@Override
	public void onEventMsg(Msg4Event msg) {
		for(HandleMessageListener currentListener : listeners){
			currentListener.onEventMsg(msg);
		}
	}

	@Override
	public void onLinkMsg(Msg4Link msg) {
		for(HandleMessageListener currentListener : listeners){
			currentListener.onLinkMsg(msg);
		}
	}

	@Override
	public void onLocationMsg(Msg4Location msg) {
		for(HandleMessageListener currentListener : listeners){
			currentListener.onLocationMsg(msg);
		}
	}

	@Override
	public void onErrorMsg(int errorCode) {
		for(HandleMessageListener currentListener : listeners){
			currentListener.onErrorMsg(errorCode);
		}
	}


	/* (non-Javadoc)
	 * @see org.marker.weixin.Session#onVoiceMsg(org.marker.weixin.msg.Msg4Voice)
	 */
	@Override
	public void onVoiceMsg(Msg4Voice msg) {
		for(HandleMessageListener currentListener : listeners){
			currentListener.onVoiceMsg(msg);
		}
	}


	/* (non-Javadoc)
	 * @see org.marker.weixin.Session#onVideoMsg(org.marker.weixin.msg.Msg4Video)
	 */
	@Override
	public void onVideoMsg(Msg4Video msg) {
		for(HandleMessageListener currentListener : listeners){
			currentListener.onVideoMsg(msg);
		}
	}



}
