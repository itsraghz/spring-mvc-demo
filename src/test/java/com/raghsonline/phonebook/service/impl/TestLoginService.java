package com.raghsonline.phonebook.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.raghsonline.springmvc.LoginService;

public class TestLoginService {

	static LoginService loginService = new LoginService();
	static Logger logger = Logger.getLogger(TestLoginService.class);
	
	@Test
	@DisplayName("A Test method to verify Login credentials from DB")
	public void verifyUser()
	{
		boolean isValidUser = false;
		isValidUser = loginService.isValidUser("Java", "Spring");
		
		logger.info("isValidUser" +isValidUser);
		assertTrue(isValidUser);
		
	}
}
