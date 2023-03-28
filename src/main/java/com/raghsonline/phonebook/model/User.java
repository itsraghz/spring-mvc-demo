package com.raghsonline.phonebook.model;

import org.apache.log4j.Logger;

public class User {
	
	Logger logger = Logger.getLogger(User.class);
	int id;
	String userName;
	String password;
	
	public User()
	{
		logger.info("User() instantiated...");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [hashCode()=" + hashCode() + ", id=" + id + ", userName=" + userName + "]";
	}

	
	

}
