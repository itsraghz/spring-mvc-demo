package com.raghsonline.phonebook.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.raghsonline.springmvc.LoginService;

public class TestUserServiceImpl {
	
	Logger logger = Logger.getLogger(TestUserServiceImpl.class);

	LoginService loginService = new LoginService();
	
	@Test
	@DisplayName("verifying User from DB")
	public void verifyUser()
	{
		boolean isValidUser = false;
		isValidUser = loginService.isValidUser("TestJava", "TestSpring");

		logger.info("isValidUser" +isValidUser);
		assertTrue(isValidUser);

	}
}
