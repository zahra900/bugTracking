package com.zahra.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zahra.app.model.Project;
import com.zahra.app.service.ProjectService;


@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping("/project")
	public Project createProject(@RequestBody Project project ) {
			return projectService.createProject(project);
	}
	
	@GetMapping("/{word}")
	public Page<Project> searchProject(@RequestParam(name="page") int page,
	        						   @RequestParam(name="size") int size,
	        						   @PathVariable(value = "word") String word) {
		Pageable paging = PageRequest.of(page, size, Sort.by(Direction.DESC,"id"));
		return projectService.searchProject(word,paging);
	}

	
	@PutMapping("/project")
	public Project updateProject(@RequestBody Project project ) {
		return projectService.updateProject(project);
    }
	
	@GetMapping("/project/{id}")
	public Project getProject(@PathVariable(value = "id") long id) {
			Project project = projectService.getProject(id);
			return project;
	}
	
	@GetMapping("/page")
	public Page<Project> getPage( 
			@RequestParam(name="page") int page,
	        @RequestParam(name="size") int size) {
		    Pageable paging = PageRequest.of(page, size, Sort.by(Direction.DESC,"id"));
			return projectService.getPage(paging);
	}
	
	
	@GetMapping
	public List<Project> getProjects() {
			return projectService.getProjects();
	}
	
	@DeleteMapping("/project/{id}")
	public void delete(@PathVariable(value = "id") long id) {
		projectService.delete(id);
    }
	
}
