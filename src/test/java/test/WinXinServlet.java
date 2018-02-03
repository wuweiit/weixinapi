package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.marker.utils.MySecurity;
import org.marker.weixin.Session;
import org.marker.weixin.msg.Msg4Event;
import org.marker.weixin.msg.Msg4Image;
import org.marker.weixin.msg.Msg4Link;
import org.marker.weixin.msg.Msg4Location;
import org.marker.weixin.msg.Msg4Text;
import org.marker.weixin.msg.Msg4Video;
import org.marker.weixin.msg.Msg4Voice;

/**
 * 处理微信服务器请求的Servlet URL地址：http://xxx/weixin/dealwith.do
 * 
 * 注意：官方文档限制使用80端口哦！
 * 
 * @author marker
 * 个人博客： www.yl-blog.com
 * 微博： http://t.qq.com/wuweiit
 */
public class WinXinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// TOKEN 是你在微信平台开发模式中设置的哦
	public static final String TOKEN = "";

	/**
	 * 处理微信服务器验证
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");// 随机字符串

		// 重写totring方法，得到三个参数的拼接字符串
		List<String> list = new ArrayList<String>(3) {
			private static final long serialVersionUID = 2621444383666420433L;
			public String toString() {
				return this.get(0) + this.get(1) + this.get(2);
			}
		};
		list.add(TOKEN);
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);// 排序
		String tmpStr = new MySecurity().encode(list.toString(),
				MySecurity.SHA_1);// SHA-1加密
		Writer out = response.getWriter();
		if (signature.equals(tmpStr)) {
			out.write(echostr);// 请求验证成功，返回随机码
		} else {
			out.write("");
		}
		out.flush();
		out.close();
	}

	
	/**
	 * 处理微信服务器发过来的各种消息，包括：文本、图片、地理位置、音乐等等
	 * 
	 * 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		InputStream is = request.getInputStream();
		OutputStream os = response.getOutputStream();
		
		 
		
		@SuppressWarnings("unused")
		Session session = new Session( ) {

			@Override
			public void onTextMsg(Msg4Text msg) { 
				System.out.println(msg.getToUserName());
				System.out.println("收到消息"+msg.getContent());
				
				//回复一条消息
				Msg4Text reMsg = new Msg4Text();
				reMsg.setFromUserName(msg.getToUserName());
				reMsg.setToUserName(msg.getFromUserName());
				reMsg.setCreateTime(msg.getCreateTime());
				 
				reMsg.setContent("呵呵,谢谢您给我发消息");
				reMsg.setFuncFlag("0"); 
				
				callback(reMsg);//回传消息
			}

			@Override
			public void onImageMsg(Msg4Image msg) {
				System.out.println("收到图片"+msg.getPicUrl());
			}

			@Override
			public void onEventMsg(Msg4Event msg) {
				String eType = msg.getEvent();
				if(Msg4Event.SUBSCRIBE.equals(eType)){
					System.out.println("关注人："+msg.getFromUserName());
					
				}else if(Msg4Event.UNSUBSCRIBE.equals(eType)){
					System.out.println("取消关注人："+msg.getFromUserName());
					
				}else{//点击：暂时内测
					System.out.println("反正有事件推过来");
				}
				
			}

			@Override
			public void onLinkMsg(Msg4Link msg) {
				System.out.println("收到URL："+msg.getUrl());
			}

			@Override
			public void onLocationMsg(Msg4Location msg) {
				System.out.println("收到地理位置消息：");
				System.out.println("X:"+msg.getLocation_X());
				System.out.println("Y:"+msg.getLocation_Y());
				System.out.println("Scale:"+msg.getScale());
			}

			@Override
			public void onErrorMsg(int errorCode) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onVoiceMsg(Msg4Voice msg) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onVideoMsg(Msg4Video msg) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		
		session.process(is, os);
		session.close();
		
	}

}
