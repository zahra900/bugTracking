package com.zahra.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.zahra.app.model.User;
import com.zahra.app.repository.UserRepository;
/*Spring Security needs you to define two beans to get authentication up and running.
A UserDetailsService.
A PasswordEncoder.*/

public class UserDetailsServiceImp implements UserDetailsService {
	 
	@Autowired
	 private UserRepository userRepository;
	
	 @Override
	 public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
	       
		   User user = userRepository.findByEmail(email);
		   
		   UserBuilder builder = null;
		   if (user != null) {
			      builder = org.springframework.security.core.userdetails.User.withUsername(email);
			      builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
			      builder.roles(user.getRole().getRoleName());
			    } else {
			      throw new UsernameNotFoundException("User not found.");
			    }

			    return builder.build();
			  }
	  
}
