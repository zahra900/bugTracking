package com.zahra.app.service;

import java.util.List;

import com.zahra.app.model.Role;

public interface RoleService {
	
	Role findRoleById(long id);
	
	List<Role> getAll();
}
