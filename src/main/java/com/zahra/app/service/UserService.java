package com.zahra.app.service;

import java.util.List;

import com.zahra.app.model.User;

public interface UserService {

	 User save(User user);
	 List<User> findAll();
	 User findByEmail(String email);
	 String getCurrentUser();
	 User updateUserRole(User user);
}
