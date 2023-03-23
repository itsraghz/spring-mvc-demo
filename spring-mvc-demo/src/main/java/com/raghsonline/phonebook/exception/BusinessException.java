package com.raghsonline.phonebook.exception;

/**
 * <p>
 * A custom exception to handle all the business logic 
 * validation failures
 * </p>
 * @author raghavan.muthu
 * @since 2023-03-20
 * @version 1.0
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 4279489712957300214L;

	public BusinessException() 
	{
		super();
	}

	/**
	 * <p>
	 * This is the most likely used version for our purposes now.
	 * </p>
	 * @param message the error message to be thrown
	 */
	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
