package org.marker.weixin.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.marker.weixin.Session;
import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4Image;
import org.marker.weixin.msg.Msg4Link;
import org.marker.weixin.msg.Msg4Location;
import org.marker.weixin.msg.Msg4Text;
import org.marker.weixin.msg.Msg4Video;
import org.marker.weixin.msg.Msg4Voice;

public class mTest {

	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		 
		InputStream inputStream = new FileInputStream("c://a.txt");
		OutputStream os = new FileOutputStream("d://a.txt");
		
		Session session = new Session() {

			@Override
			public void onTextMsg(Msg4Text msg) { 
				System.out.println(msg.getToUserName());
				System.out.println("收到消息"+msg.getContent());
				Msg4Text reMsg = new Msg4Text();
				reMsg.setFromUserName(msg.getToUserName());
				reMsg.setToUserName(msg.getFromUserName());
				reMsg.setCreateTime(msg.getCreateTime());
			 
				reMsg.setContent("呵呵,谢谢您给我发消息");
				reMsg.setFuncFlag("0"); 
				callback(reMsg);
			}

			@Override
			public void onImageMsg(Msg4Image msg) { 
				
			}

			@Override
			public void onEventMsg(Msg4Event msg) { 
				System.out.println("接收到事件消息");
			}

			@Override
			public void onLinkMsg(Msg4Link msg) { 
				
			}

			@Override
			public void onLocationMsg(Msg4Location msg) { 
				
			}

			@Override
			public void onErrorMsg(int errorCode) { 
				
			}

			@Override
			public void onVoiceMsg(Msg4Voice msg) {
				System.out.println(msg.getRecognition());
			}

			@Override
			public void onVideoMsg(Msg4Video msg) {
				// TODO Auto-generated method stub
				
			}
			
		};
		 
		session.process(inputStream, os); 
		
		
	}
}

