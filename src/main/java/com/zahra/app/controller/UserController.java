package com.zahra.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.zahra.app.model.User;
import com.zahra.app.service.UserService;
import com.zahra.app.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> listUser() {
		return userServiceImpl.findAll();
	}
	
	@PostMapping("/user")
	public User create(@RequestBody User user) {
		return userServiceImpl.save(user);
	}
	
	  @PutMapping()
	  public void updateTutorial( @RequestBody List<User> users) {
		 users.forEach(element -> {
			 userService.updateUserRole(element);
		 });  
	  }
	

}
