package com.zahra.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zahra.app.model.Project;

public interface ProjectService {

	Project createProject(Project project);
	Project getProject(long id);
	List<Project> getProjects();
	void delete(long id);
	Page<Project> getPage(Pageable pageable);
	Project updateProject(Project project);
	Page<Project> searchProject(String word,Pageable pageable);
	
}
