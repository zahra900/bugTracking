package com.zahra.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zahra.app.model.Bug;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long>  {
	
	Page<Bug> findAll(Pageable pageable);
	
	Page<Bug> findByTitleContaining(String title,Pageable pageable);
}
