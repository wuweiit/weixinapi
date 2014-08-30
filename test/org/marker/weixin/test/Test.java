package org.marker.weixin.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.marker.weixin.msg.Msg;
import org.marker.weixin.msg.Msg4Head;
import org.marker.weixin.msg.Msg4Text;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Test {

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {

		InputStream inputStream = new FileInputStream("c://a.txt");
 
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		
		Document document = builder.parse(inputStream);
		
		
		
		
		
		Msg4Head head = new Msg4Head();
		head.read(document);
		String type = head.getMsgType();
		Msg msg = null;
		if(Msg.MSG_TYPE_TEXT.equals(type)){//文本消息
			msg = new Msg4Text(head);
			msg.read(document); 
		} 
		Msg4Text a = (Msg4Text)msg;
		System.out.println(a.getContent());
	}
}
