package org.marker.weixin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.marker.weixin.msg.Msg;
import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4Head;
import org.marker.weixin.msg.Msg4Image;
import org.marker.weixin.msg.Msg4Link;
import org.marker.weixin.msg.Msg4Location;
import org.marker.weixin.msg.Msg4Text;
import org.marker.weixin.msg.Msg4Video;
import org.marker.weixin.msg.Msg4Voice;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


/**
 * 抽象会话
 * 此会话声明周期在一个请求响应内。
 * 通过继承类实现各种消息的处理方法
 * @author marker
 * */
public abstract class Session {

	/** 时间格式化 */
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	
	//输入流
	private InputStream is;
	//输出流
	private OutputStream os;
	
	/** Document构建类 */
	private static DocumentBuilder builder;
	private static TransformerFactory tffactory;
	  
	static{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		//格式化工厂对象
		tffactory = TransformerFactory.newInstance();
	}
	
	/**
	 * Session 
	 * 
	 */
	public Session() { }


	
	/**
	 * 解析微信消息，并传递给对应方法
	 * @param is 输入流
	 * @param os 输出流
	 */
	public void process(InputStream is, OutputStream os) {
		this.os = os;
		try {
			Document document = builder.parse(is);
			Msg4Head head = new Msg4Head();
			head.read(document);
			String type = head.getMsgType();
		 
			if(Msg.MSG_TYPE_TEXT.equals(type)){//文本消息
				Msg4Text msg = new Msg4Text(head);
				msg.read(document);
				this.onTextMsg(msg);
			}else if(Msg.MSG_TYPE_IMAGE.equals(type)){//图片消息
				Msg4Image msg = new Msg4Image(head);
				msg.read(document);
				this.onImageMsg(msg);
			}else if(Msg.MSG_TYPE_EVENT.equals(type)){//事件推送
				Msg4Event msg = new Msg4Event(head);
				msg.read(document);
				this.onEventMsg(msg);
			}else if(Msg.MSG_TYPE_LINK.equals(type)){//链接消息
				Msg4Link msg = new Msg4Link(head);
				msg.read(document);
				this.onLinkMsg(msg);
			}else if(Msg.MSG_TYPE_LOCATION.equals(type)){//地理位置消息
				Msg4Location msg = new Msg4Location(head);
				msg.read(document);
				this.onLocationMsg(msg);
			}else if(Msg.MSG_TYPE_VOICE.equals(type)){
				Msg4Voice msg = new Msg4Voice(head);
				msg.read(document);
				this.onVoiceMsg(msg);
			}else if(Msg.MSG_TYPE_VIDEO.equals(type)){
				Msg4Video msg = new Msg4Video(head);
				msg.read(document);
				this.onVideoMsg(msg);
			}else{
				this.onErrorMsg(-1);//这里暂时这样处理的
			}
		} catch (SAXException e) { 
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}






	/**
	 * 回传消息给微信服务器
	 * 只能再接收到微信服务器消息后，才能调用此方法
	 * @param msg 消息对象（支持：文本、音乐、图文）
	 * */
	public void callback(Msg msg){
		Document document = builder.newDocument();
		msg.write(document);
		try {
			Transformer transformer = tffactory.newTransformer();
			
			;
			transformer.transform(new DOMSource(document), new StreamResult(new OutputStreamWriter(os,"utf-8")));
		} catch ( Exception e) { 
			e.printStackTrace();// 保存dom至目输出流
		}
	}
	
	
	/**
	 * 关闭
	 * */
	public void close(){
		try {
			if(is != null){
				is.close();
			}
			if(os != null){
				os.flush();
				os.close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 收到文本消息
	 * @param msg
	 */
	public abstract void onTextMsg(Msg4Text msg);
	
	/**
	 * 收到图片消息
	 * @param msg
	 */
	public abstract void onImageMsg(Msg4Image msg);
	
	/**
	 * 收到事件推送消息
	 * @param msg
	 */
	public abstract void onEventMsg(Msg4Event msg);
	
	/**
	 * 收到链接消息
	 * @param msg
	 */
	public abstract void onLinkMsg(Msg4Link msg);
	
	/**
	 * 收到地理位置消息
	 * @param msg
	 */
	public abstract void onLocationMsg(Msg4Location msg);
	
	/**
	 * 收到语音识别消息
	 * @param msg
	 */
	public abstract void onVoiceMsg(Msg4Voice msg);
	

	/**
	 * 收到视频消息
	 * @param msg
	 */
	public abstract void onVideoMsg(Msg4Video msg);
	
	
	/**
	 * 错误消息
	 * @param msg
	 */
	public abstract void onErrorMsg(int errorCode);
 
	
}
