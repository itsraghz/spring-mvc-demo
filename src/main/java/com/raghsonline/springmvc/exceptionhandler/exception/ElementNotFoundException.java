package com.raghsonline.springmvc.exceptionhandler.exception;
/**
 * <p>
 * 
 * </p>
 * @author raghavan.muthu
 *
 */
public class ElementNotFoundException extends Exception
{
	private static final long serialVersionUID = -6394676322872335247L;
	
	public ElementNotFoundException() {
		super();
	}

	public ElementNotFoundException(String message) {
		super(message);
	}
}