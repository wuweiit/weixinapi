package org.marker.weixin.msg;

import java.util.ArrayList;
import java.util.List;

import org.marker.weixin.WXXmlElementName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 图文消息
 * @author marker
 * @date 2013-8-25 上午8:53:43
 * @version 1.0
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 */
public class Msg4ImageText extends Msg {

	// 图文消息个数，限制为10条以内
	private String articleCount;
	// 图文消息的数据
	private List<Data4Item> items = new ArrayList<Data4Item>(3);
	// 位0x0001被标志时，星标刚收到的消息。
	private String funcFlag;
	
	
	/**
	 * 默认构造
	 * */
	public Msg4ImageText() {
		this.head = new Msg4Head();
		this.head.setMsgType(Msg.MSG_TYPE_IMAGE_TEXT);
	}
	
	@Override
	public void write(Document document) {
		Element root = document.createElement(WXXmlElementName.ROOT);
		head.write(root, document);
		Element articleCountElement = document.createElement(WXXmlElementName.ARTICLE_COUNT);
		articleCountElement.setTextContent(this.articleCount);
		
		Element articlesElement = document.createElement(WXXmlElementName.ARTICLES);
		int size = Integer.parseInt(this.articleCount);
		for(int i = 0; i<size; i++){
			Data4Item currentItem = items.get(i);//获取当前
			Element itemElement = document.createElement(WXXmlElementName.ITEM);
			Element titleElement = document.createElement(WXXmlElementName.TITLE);
			titleElement.setTextContent(currentItem.getTitle());
			Element descriptionElement = document.createElement(WXXmlElementName.DESCRITION);
			descriptionElement.setTextContent(currentItem.getDescription());
			Element picUrlElement = document.createElement(WXXmlElementName.PIC_URL);
			picUrlElement.setTextContent(currentItem.getPicUrl());
			Element urlElement = document.createElement(WXXmlElementName.URL);
			urlElement.setTextContent(currentItem.getUrl());
			itemElement.appendChild(titleElement);
			itemElement.appendChild(descriptionElement);
			itemElement.appendChild(picUrlElement);
			itemElement.appendChild(urlElement);
			
			articlesElement.appendChild(itemElement);
		}
		
		Element funcFlagElement = document.createElement(WXXmlElementName.FUNC_FLAG);
		funcFlagElement.setTextContent(this.funcFlag);
		
		

		root.appendChild(articleCountElement);
		root.appendChild(articlesElement);
		
		document.appendChild(root);
	}

	@Override
	public void read(Document document) {
		// TODO Auto-generated method stub
		
	}

	public String getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(String funcFlag) {
		this.funcFlag = funcFlag;
	}

	
	
	public void addItem(Data4Item item){
		this.items.add(item);
		this.reflushArticleCount();
	}
	
	public void removeItem(int index){
		this.items.remove(index);
		this.reflushArticleCount();
	}
	
	
	
	/**
	 * 刷新当前文章条数
	 * */
	private void reflushArticleCount(){
		this.articleCount = ""+this.items.size();
	}
	
	
	
	
}
