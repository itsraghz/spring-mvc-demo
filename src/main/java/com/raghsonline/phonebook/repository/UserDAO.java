package com.raghsonline.phonebook.repository;

import java.util.Optional;

public interface UserDAO<T> {

	public Optional<T> verifyUser(String s1, String s2);

	long getCount();
	}

