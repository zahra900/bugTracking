package com.zahra.app.service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zahra.app.model.Project;
import com.zahra.app.model.User;
import com.zahra.app.repository.ProjectRepository;


@Service
public class ProjectServiceImpl implements ProjectService {

	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	public Project createProject(Project project) {
	   Set<User> users = project.getUsers().stream()
			      .map(user -> userServiceImpl.findByEmail(user.getEmail()))
			      .collect(Collectors.toSet());
		project.setUsers(users);
		return  projectRepository.save(project);
	}
	
	public Project getProject(long id) {
//	   Optional<Project> optionalProject = projectRepository.findById(id);
//	   Project project = optionalProject.isPresent() ? optionalProject.get() : null;
	   Project project = projectRepository.findProjectById(id);
	   return project;
	}
	
	public List<Project> getProjects(){
		return projectRepository.findAll();
	}
	
	public Page<Project> getPage(Pageable paging) {
		return projectRepository.findAll(paging);
	}
	
	
	public void delete(long id) {
		projectRepository.deleteById(id);
	}

	@Override
	public Project updateProject(Project project) {
		Project modifiedProject = getProject(project.getId());
		Set<User> users  = new HashSet<>();
		project.getUsers().forEach(user -> {
			User u = userServiceImpl.findByEmail(user.getEmail());
			users.add(u);
		});
		project.setBugs(modifiedProject.getBugs());
		project.setUsers(users);
		
		return projectRepository.save(project);
	}

	@Override
	public Page<Project> searchProject(String word,Pageable paging) {
		return projectRepository.findByProjectNameContaining(word,paging);
	}
	
}
