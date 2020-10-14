package com.zahra.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zahra.app.model.Role;
import com.zahra.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT DISTINCT user FROM User user " +
            "INNER JOIN FETCH user.role AS role " +
            "WHERE user.email = :email")
    User findByEmail(@Param("email") String email);
	
	List<User> findByRoleNot(Role role);
	
	
}
