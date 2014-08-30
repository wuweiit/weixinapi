package org.marker.weixin.msg;

import java.util.ArrayList;
import java.util.List;
import org.marker.weixin.exception.WeixinMenuOutOfBoundException;


/**
 * Button
 * 
 * @author marker
 * @date 2014年8月30日
 * @version 1.0
 */
public class Data4Button {

	// 一级菜单数组，个数应为1~3个
	public List<Data4Menu> button = new ArrayList<Data4Menu>(3);

	
	/**
	 * 添加一级菜单
	 * @param menu 
	 * @throws WeixinMenuOutOfBoundException
	 */
	public void addMenu(Data4Menu menu) throws WeixinMenuOutOfBoundException {
		if(button.size() < 3){
			button.add(menu);
		}else{
			throw new WeixinMenuOutOfBoundException();
		}
	}
	
	
}
