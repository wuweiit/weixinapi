package org.marker.weixin.msg;

import java.util.ArrayList;
import java.util.List;

import org.marker.weixin.exception.WeixinSubMenuOutOfBoundException;


/**
 * 	click：
 *		用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为event 的结构给开发者（参考消息接口指南），并且带上按钮中开发者填写的key值，开发者可以通过自定义的key值与用户进行交互；
 *	view：	
 *		用户点击view类型按钮后，微信客户端将会打开开发者在按钮中填写的url值 （即网页链接），达到打开网页的目的，建议与网页授权获取用户基本信息接口结合，获得用户的登入个人信息。
 *
 * @author marker
 * @date 2014年8月30日
 * @version 1.0
 */
public class Data4Menu {
	
	/** click类型  */
	public static final String TYPE_CLICK = "click";
	
	/** view类型  */
	public static final String TYPE_VIEW  = "view";
	
	
	
	// 菜单的响应动作类型，目前有click、view两种类型
	private String type;
	
	//菜单标题，不超过16个字节，子菜单不超过40个字节
	private String name;
	
	//click类型必须	 菜单KEY值，用于消息接口推送，不超过128字节
	private String key;
	
	// view类型使用
	private String url;
	
	// 二级菜单数组，个数应为1~5个
	private List<Data4Menu> sub_button = new ArrayList<Data4Menu>(5);
	
	
	// 子菜单
	public Data4Menu(String type, String name , String item) {
		this.type = type;
		this.name = name;
		if(TYPE_CLICK.equals(type)){
			this.key = item;
		}else if(TYPE_VIEW.equals(type)){
			this.url = item;
		}
	}

	
	/**
	 * 添加子菜单
	 * @param menu
	 * @throws WeixinSubMenuOutOfBoundException
	 */
	public void addSubMenu(Data4Menu menu) throws WeixinSubMenuOutOfBoundException{
		if(sub_button.size() < 5){
			sub_button.add(menu);
		}else{
			throw new WeixinSubMenuOutOfBoundException();
		}
	}




	public String getType() {
		return type;
	}





	public void setType(String type) {
		this.type = type;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getKey() {
		return key;
	}





	public void setKey(String key) {
		this.key = key;
	}





	public String getUrl() {
		return url;
	}





	public void setUrl(String url) {
		this.url = url;
	}





	public List<Data4Menu> getSub_button() {
		return sub_button;
	}





	public void setSub_button(List<Data4Menu> sub_button) {
		this.sub_button = sub_button;
	}
	
	
	 
	
}
