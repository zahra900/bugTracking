package com.zahra.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zahra.app.model.Role;
import com.zahra.app.repository.RoleRepository;


@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Override
    public Role findRoleById(long id) {
        return roleRepository.findRoleById(id);
    }
	
	@Override
	public List<Role> getAll() {
        return roleRepository.findAll();
    }

}
