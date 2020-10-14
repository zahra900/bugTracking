package com.zahra.app.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.zahra.app.model.Bug;
import com.zahra.app.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
      
	List<Comment> findByBug(Bug bug);
	
	List<Comment> findByOrderByIdDesc();
	
}
