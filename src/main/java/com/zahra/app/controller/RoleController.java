package com.zahra.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zahra.app.model.Role;
import com.zahra.app.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService; 
	
	@GetMapping
	public List<Role> getRoles()
	{
		return roleService.getAll();
	}

}
