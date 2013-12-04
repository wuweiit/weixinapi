/**
 *  
 *  吴伟 版权所有
 */
package org.marker.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ContentHandler;
import java.net.ContentHandlerFactory;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author marker
 * @date 2013-11-17 下午6:06:19
 * @version 1.0
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 */
public class menuTest {

	public static void main(String[] args) throws IOException {
		String url ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=cms";
		
		
		URL u = new URL(url);
		
		HttpsURLConnection  conn =  (HttpsURLConnection) u.openConnection(); 
		conn.setRequestMethod("POST"); 
		OutputStream os = conn.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
		PrintWriter pw = new PrintWriter(osw);
		
		String json = " {\"button\":[{\"type\":\"click\", \"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"}]}";
		pw.write(json);
		pw.flush();
		pw.close();
	}
}
