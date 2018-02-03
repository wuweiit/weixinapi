package utils;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.marker.utils.MenuUtil;
import org.marker.utils.TicketUtils;
import org.marker.weixin.exception.WeixinException;
import org.marker.weixin.msg.Data4Button;
import org.marker.weixin.msg.Data4Menu;


/**
 * 微信菜单测试代码
 * @author marker
 * 时间： 2018年2月3日
 * @version 1.0
 */
public class TicketTest {

   	@Test
	public void test() throws WeixinException {


        String accessToken = MenuUtil.getAccessToken();
        String ticket = TicketUtils.getJsapiTicket(accessToken);
        System.out.println(ticket);


	}
}
