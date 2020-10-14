package com.zahra.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zahra.app.model.Project;


@Repository
public interface ProjectRepository  extends JpaRepository<Project, Long> {
	
	Page<Project> findAll(Pageable pageable);
	
	@Query(value = "SELECT p FROM Project p left join fetch  p.bugs  WHERE p.id = :id")
    Project findProjectById(@Param("id") long id);
	
	Page<Project> findByProjectNameContaining(String title,Pageable pageable);
	
}
