package org.marker.weixin.test;
 
import org.marker.utils.MenuUtil;
import org.marker.weixin.exception.WeixinException;
import org.marker.weixin.msg.Data4Button;
import org.marker.weixin.msg.Data4Menu;

import com.alibaba.fastjson.JSON;


/**
 * 微信菜单测试代码
 * @author marker
 * @date 2014年8月30日
 * @version 1.0
 */
public class ButtonTest {

	public static void main(String[] args) {
		
		// 创建按钮
		Data4Button btn = new Data4Button();
		
		// 创建一级菜单
		Data4Menu menu1 = new Data4Menu("view","关于我们","http://cms.yl-blog.com/about.html");
		Data4Menu menu2 = new Data4Menu("click","guanyuds","Btn_a");
			// 二级菜单
			Data4Menu sm_1 = new Data4Menu("click", "赞助我们", "btn_zanzu");
		
		Data4Menu menu3 = new Data4Menu("click","guanyuds","Btn_5");
			Data4Menu sn_1 = new Data4Menu("click","guanyuds","Btn_2");
			
		try {
			// 菜单之间的关系
			btn.addMenu(menu1);
			menu2.addSubMenu(sm_1);
			btn.addMenu(menu2);
			
			menu3.addSubMenu(sn_1);
			
			btn.addMenu(menu3); 
	 
		// Object -> json
		String menustr = JSON.toJSONString(btn);
		
		// weixinapi更具微信规范，添加了异常机制。
		/*
		 * 比如：菜单操作的异常，一级菜单最多三个，超出就抛出异常 
		 * 
		 * */ 
			MenuUtil.create(menustr);
		} catch (WeixinException e) { 
			e.printStackTrace();
		}
		
	}
}
