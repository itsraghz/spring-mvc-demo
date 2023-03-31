package com.raghsonline.phonebook.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleException(MethodArgumentNotValidException ex)
	{
		Map<String, String> errorMap = new HashMap<>();
		
		/* 
		 * The control comes here only when there is an exception.
		 * Hence, there is no need to check the BindingResult.hasErrors()!
		 */
		ex.getBindingResult()
			.getFieldErrors()
			/*.forEach(x -> logger.error("Error on field [" + x.getField() 
							+ ", message : " + x.getDefaultMessage()));*/
			.forEach(x -> errorMap.put(x.getField(), x.getDefaultMessage()));
		
		//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation Errors occurred!");
		
		/*
		 * We are missing the Http Status - as we are NOT sending a ResponseEntity as a return value! 
		 * Solution: Add an Annotation to this method called - `@ResponseStatus` that will solve.
		 */
		return errorMap;
	}
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleException2(BusinessException ex)
	{
		logger.error("handleException2 - BusinessException occurred");
		logger.error("Exception : " + ex);
		logger.error("Error Message : " + ex.getMessage());
		
		return ex.getMessage();
	}
}
