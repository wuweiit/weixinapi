package org.marker.utils; 

import org.marker.config.Config;
import org.marker.weixin.exception.WeixinException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
 
/**
 * 菜单工具类
 * 提供创建、删除、查询菜单
 * @author marker
 * @date 2014年8月30日
 * @version 1.0
 */
public class MenuUtil {
	// 获取tokenURL
	public static final String URL_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token";
	
	// 创建菜单URL
	public static final String URL_MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create";
	
	// 获取菜单URL
	public static final String URL_MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get";
	
	// 删除菜单URL
	public static final String URL_MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete";
	
	
	
	
	// 获取access_token的时间
	private static long getTime;
	
	// 当前获取的access_token(不用每次获取)
	private static String access_token;
	
	
	
	/**
	 * 获取access_token
	 * @return
	 * @throws WeixinException
	 */
	private static String getAccessToken() throws WeixinException{
		if(null != access_token){// 已经获取了access_token
			long currentTime = System.currentTimeMillis();
			if((currentTime - getTime) < 7200000 ){// 过期了  | access_token有效期为7200秒
				return access_token;
			}
		}
		
		// 从服务端从新获取access_token
		String url = URL_ACCESSTOKEN + "?" + "grant_type="+Config.grant_type+"&appid="+Config.APPID+"&secret="+Config.SECRET;
		
		String json = HttpUtil.sendHttpsGET(url);
		getTime = System.currentTimeMillis();
		JSONObject obj = JSON.parseObject(json);
		String access_token = obj.getString("access_token");
		if(null == access_token){// 错误
			throw new WeixinException(json); 
		}
		return access_token;
	}
	
	
	
	
	/**
	 * 创建菜单
	 * @param json
	 * @throws WeixinException
	 */
	public static void create(String json) throws WeixinException{
		String url = URL_MENU_CREATE+ "?access_token=" + getAccessToken();
		String result = HttpUtil.sendHttpsPOST(url , json); 
		JSONObject obj = JSON.parseObject(result); 
		int errcode = obj.getIntValue("errcode");
		if(errcode > 0){
			throw new WeixinException(result);
		}
	}

	
	/**
	 * 查询菜单
	 * @return
	 * @throws WeixinException
	 */
	public static String get() throws WeixinException{
		String url = URL_MENU_GET + "?access_token=" + getAccessToken();
		return HttpUtil.sendHttpsGET(url);
	}
	
	
	
	/**
	 * 删除菜单
	 * @throws WeixinException
	 */
	public static void delete() throws WeixinException {
		String url = URL_MENU_DELETE + "?access_token=" + getAccessToken();
		String result = HttpUtil.sendHttpsGET(url);
		JSONObject obj = JSON.parseObject(result); 
		int errcode = obj.getIntValue("errcode");
		if(errcode > 0){
			throw new WeixinException(result);
		}
	}
	
}

