package org.marker.weixin.exception;

public class WeixinMenuOutOfBoundException extends WeixinException{
 
	private static final long serialVersionUID = 1119630888551409929L;
	
	
	public WeixinMenuOutOfBoundException() {
		super(" menu max count 3");
	}
	
}
