package com.zahra.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zahra.app.model.Role;
import com.zahra.app.model.User;
import com.zahra.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private RoleServiceImpl roleServiceImpl;
	
	@Autowired
	private RoleService roleService;
	
	
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		Role roleAdmin = roleService.findRoleById(1);
		userRepository.findByRoleNot(roleAdmin).iterator().forEachRemaining(list::add);
		list.sort((a,b)->  (int) (b.getId() - a.getId()));
		return list;
	}

	@Override
    public User save(User user) {
        user.setRole(roleServiceImpl.findRoleById(2));
        return userRepository.save(user);
    }
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		return currentPrincipalName;
    }
	
	@Override
	public User updateUserRole(User user) {
		 User modifiedUser = findByEmail(user.getEmail());
		 modifiedUser.setRole(roleService.findRoleById(user.getRole().getId()));
		 return userRepository.save(modifiedUser);
	}
}
