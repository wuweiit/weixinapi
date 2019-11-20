package test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.marker.weixin.api.WeixinApiUtils;
import org.marker.weixin.api.domain.UserInfo;
import org.marker.weixin.api.domain.WxMaTemplateData;
import org.marker.weixin.api.domain.WxMaTemplateInfo;
import org.marker.weixin.exception.WeixinException;

import java.util.ArrayList;
import java.util.List;

public class WexinApiTest {


    @Test
    public   void test1( ) throws WeixinException {


        List<String> oo = WeixinApiUtils.getUserOpenIdList(null);

        System.out.println(JSON.toJSONString(oo));
    }


	@Test
	public void test( ) throws WeixinException {


//		String openid = "o30Ze1v3-eOb7WFM8f6mbXKMBu84";
		String openid = "o30Ze1qBvWL04N5xkvrKmHA0_JF8";
		String templateid = "D0daQ7b7vEOd0kQ86HsdzmXYNsA7GMb6DxuuJst-pwA";

		List<WxMaTemplateData> list = new ArrayList<>();
		list.add(new WxMaTemplateData("first", "四川轻工业大学"));
		list.add(new WxMaTemplateData("keyword1", "A列空调"));
		list.add(new WxMaTemplateData("keyword2", "报警内容说明"));
		list.add(new WxMaTemplateData("keyword3", "低温"));
		list.add(new WxMaTemplateData("keyword4", "低温"));
		list.add(new WxMaTemplateData("remark", "低温"));

		WeixinApiUtils.sendTemplateMsg(openid, templateid, list);

		
	}



    @Test
    public void test2() throws WeixinException {
        String openid = "o30Ze1v3-eOb7WFM8f6mbXKMBu84";

        UserInfo userInfo = WeixinApiUtils.getUserInfo(openid);

        System.out.println(JSON.toJSONString(userInfo));
    }

    /**
     * 获取模板列表
     * @throws WeixinException
     */
    @Test
    public void test3() throws WeixinException {
        List<WxMaTemplateInfo> list = WeixinApiUtils.getTemplateList();

        System.out.println(JSON.toJSONString(list));
    }
}

