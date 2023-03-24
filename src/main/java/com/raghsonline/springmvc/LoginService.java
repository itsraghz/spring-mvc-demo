package com.raghsonline.springmvc;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LoginService 
{
	Logger logger = Logger.getLogger(LoginService.class);
	
	/* Add a no-arg constructor to verify the Spring Container 
	 * instantiates this class */
	public LoginService()
	{
		//System.out.println("LoginService instantiated...");
		logger.info("LoginService instantiated...");
	}
	
	public boolean isValidUser(String userName, String password)
	{
		//Just for the sake of testing, but this is NOT a recommended approach
		//In real time, we should be testing the credentials against the DB.
		//return userName.equals("Java") && password.equals("Spring");
		return userName.equalsIgnoreCase(password);
	}
}
