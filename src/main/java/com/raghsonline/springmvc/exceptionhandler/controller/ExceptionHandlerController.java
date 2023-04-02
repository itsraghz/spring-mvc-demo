package com.raghsonline.springmvc.exceptionhandler.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.raghsonline.springmvc.exceptionhandler.exception.ElementNotFoundException;

@RestController
@RestControllerAdvice
public class ExceptionHandlerController 
{

	Logger logger = Logger.getLogger(ExceptionHandlerController.class);
	
	/**
	 * <p>
	 * A ceremonial method equivalent to the "Hello, World" Program in a 
	 * Standalone Java class :) 
	 * </p>
	 * @return a String welcoming the User to the Spring MVC ExceptionHandling
	 */
	@GetMapping("/api/exceptionHandler/hello")
	public String sayHello()
	{
		logger.info("/api/exceptionHandler/hello invoked!");
		
		return String.format("Hello %s", "SpringMVC Rest ExceptionHandler");
	}
	
	/**
	 * <p>
	 * A Rest API to handle the division of two numbers.
	 * </p>
	 * <p>
	 * It will throw an <tt>ArithmeticException</tt> in case the divisor (n2) is 0 (zero)!
	 * </p>
	 * @param n1 the first number or the dividend
	 * @param n2 the second number or the divisor
	 * @return a numeric value which is the remainder, OR an <tt>ArithmeticException</tt> 
	 * 			if the divisor is zero (0).
	 */
	@GetMapping("/api/exceptionHandler/divide/{n1}/{n2}")
	public long divide(@PathVariable long n1, @PathVariable long n2)
	{
		logger.info("/api/exceptionHandler/divide/{n1}/{n2} invoked");
		logger.info("n1 = " + n1 + ", n2 = " + n2);
		
		long result = n1 / n2;
		
		logger.info("division result : " + result);
		
		return result;	
	}
	
	//@ExceptionHandler(ArithmeticException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleException(ArithmeticException arithmeticException)
	{
		logger.info("handleException() invoked!");
		logger.info("Exception Object :: " + arithmeticException);
		
		/**
		 * We need to handle the following things
		 * 
		 * 1. Mandatory - HTTP Status Code (DONE)
		 * 2. Mandatory - Response Data - with the error message (DONE)
		 * 3. Optional - Custom Headers if any we want to set. (NOT NOW)
		 */
		String errorMsg = "ArithmeticException occurred - " + arithmeticException.getMessage();
		
		logger.info("errorMsg :: " + errorMsg);
		
		return errorMsg;
	}
	
	//@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<String> handleExceptionResponseEntity(ArithmeticException arithmeticException)
	{
		logger.info("handleExceptionResponseEntity() invoked!");
		logger.info("Exception Object :: " + arithmeticException);
		
		/**
		 * We need to handle the following things
		 * 
		 * 1. Mandatory - HTTP Status Code (DONE)
		 * 2. Mandatory - Response Data - with the error message (DONE)
		 * 3. Optional - Custom Headers if any we want to set. (NOT NOW)
		 */
		String errorMsg = "ArithmeticException occurred - " + arithmeticException.getMessage();
		logger.info("errorMsg :: " + errorMsg);
		
		ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
		logger.info("responseEntity :: " + responseEntity);
		
		return responseEntity;
	}
	
	//@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNPE(NullPointerException npe)
	{
		logger.info("handleNPE() invoked");
		logger.info("npe object :: " + npe);
		
		ResponseEntity<String> responseEntity = ResponseEntity
													.status(HttpStatus.BAD_REQUEST)
													.header("responseTime", LocalDateTime.now().toString())
													.body("Input is null!");
		
		logger.info("responseEntity :: " + responseEntity);
		return responseEntity;
	}
	
	//@ExceptionHandler({ArithmeticException.class, NullPointerException.class})
	//@ExceptionHandler(Exception.class)
	public ResponseEntity<String> genericExceptionHandler(Exception exception)
	{
		logger.info("genericExceptionHandler() invoked");
		logger.info("exception object :: " + exception);
		
		ResponseEntity<String> responseEntity =  ResponseEntity
													.status(HttpStatus.BAD_REQUEST)
													.header("responseTime", LocalDateTime.now().toString())
													.body("Exception Occurred :: [" + exception.getMessage() + "]");
		
		logger.info("responseEntity :: " + responseEntity);
		return responseEntity;
	}
	
	@GetMapping("/api/exceptionHandler/chatAtIndex/{input}")
	public char charAtIndex(@PathVariable String input)
	{
		logger.info("charAtIndex() invoked!");
		
		/** Purposeful simulation of a NPE from this method to see the 
		 * specific @ExceptionHandler for the `NullPointerException` being
		 * invoked by the Spring MVC.
		 */
		if(input.equalsIgnoreCase("null")) {
			input = null;
		}

		int length = input.length();
		logger.info("length : " + length);
		
		/** 
		 * Purposefully we want to simulate a StringIndexOutOfBoundsException , 
		 * which is different from the other two Exceptions handled separatley 
		 * via each @ExceptionHandler method in the @RestController class - 
		 * `ArithmeticException` and `NullPointerException`. 
		 */
		char charAtIndex = (length <=3) ? input.charAt(0) : 
									input.charAt(input.length()+1); //Always throws an error
		
		logger.info("charAtIndex : " + charAtIndex);
		
		return charAtIndex;
	}
	
	/**
	 * <p>
	 * Though strictly not recommended, we have this flavor of handling
	 * the Arithmetic Exception for our learning purposes.
	 * </p>
	 * <p>Reason: ArithmeticException is indeed a RuntimeException (that 
	 * is why we are not advised to handle in the code)
	 * </p>
	 * <p>
	 * <b>Conclusion : </b>
	 * 	<ul>
	 * 		<li><b>Pros : </b> The response data is handled appropriately</li>
	 * 		<li><b>Pros : </b> No Stack trace was thrown / HTTP Status 500 - Internal Server Error was thrown!</li>
	 * 		<li><b>Cons : </b> The Http Status code was NOT handled appropriately, 
	 * 			and it is shown as HTTP 200 (OK) which is misleading!
	 *		</li>
	 * 	</ul>
	 * </p>
	 * @param n1 the first number or the dividend
	 * @param n2 the second number or the divisor
	 * @return a numeric value which is the remainder, will be -1 in case the divisor is 0
	 * 			(programatically handled)
	 */
	@GetMapping("/api/exceptionHandler/divide/AE/{n1}/{n2}")
	public long divideCatch(@PathVariable long n1, @PathVariable long n2)
	{
		logger.info("/api/exceptionHandler/divide/{n1}/{n2} invoked");
		logger.info("n1 = " + n1 + ", n2 = " + n2);
		
		long result = 0;
		
		try {
			result = n1 / n2;
		}catch(ArithmeticException arithmeticException) {
			logger.error("ArithmeticException occurred");
			logger.error("Error Message : " + arithmeticException.getMessage());
			result = -1;
		}
		
		logger.info("division result : " + result);
		
		return result;	
	}
	
	@GetMapping("/api/exceptionHandler/strLen/")
	public int stringLen()
	{
		logger.info("stringLen() invoked!");
		
		String input = null;
		
		@SuppressWarnings("null")
		int length = input.length();
		
		logger.info("length : " + length);
		
		return length;
	}
	
	@ExceptionHandler(ElementNotFoundException.class)
	public ResponseEntity<String> handleElementNotFoundException(
			ElementNotFoundException elementNotFoundException) 
	{
		logger.info("handleElementNotFoundException() invoked!");
		logger.info("elementNotFoundException :: " + elementNotFoundException);
		
		ResponseEntity<String> responseEntity = ResponseEntity
										.status(HttpStatus.BAD_REQUEST)
										//optionally if you want
										.header("responseTime", LocalDateTime.now().toString())
										.header("Batch", "Milvik Java Training Batch- May 2022")
										.body(elementNotFoundException.getMessage());
		
		logger.info("responseEntity :: " + responseEntity);
		
		return responseEntity;
		
	}
	
	@GetMapping("/api/exceptionHandler/getRainbowColor/{codeChar}")
	public int getRainbowColorForCode(@PathVariable String codeChar) 
	throws ElementNotFoundException
	{
		logger.info("/api/exceptionHandler/getRainbowColor/{codeChar} invoked");
		logger.info("pathParam - codeChar : [" + codeChar + "]");
		
		List<String> rainbowColorsList = List.of("V", "I", "B", "G","Y", "O", "R");
		
		String color = null;
		int indexOfColor = -1;
		
		Optional<String> optionalColor = rainbowColorsList.stream().filter(x -> x.equals(codeChar)).findAny();
		logger.info("optionalColor :: " + optionalColor);
		
		if(optionalColor.isPresent()) {
			color = optionalColor.get();
			logger.info("Matching Color :: " + color);
			indexOfColor = rainbowColorsList.indexOf(color);
			logger.info("Index of the Matching Color :: " + indexOfColor);
		} else {
			throw new ElementNotFoundException("No Matching color for the code - [" + codeChar + "]");
		}
		
		return indexOfColor;
	}
}
