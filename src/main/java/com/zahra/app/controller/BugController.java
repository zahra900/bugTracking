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

import com.zahra.app.model.Bug;
import com.zahra.app.model.Project;
import com.zahra.app.service.BugService;
import com.zahra.app.service.BugServiceImpl;


@RestController
@RequestMapping("/bugs")
public class BugController{

	@Autowired
	private BugService bugService;
	
	@GetMapping
	public List<Bug> getBugs() {
		List<Bug> bugs =  bugService.getAll();
		return bugs;
	}
	
	@GetMapping("/page")
	public Page<Bug> getPage( 
			@RequestParam(name="page") int page,
	        @RequestParam(name="size") int size) {
		    Pageable paging = PageRequest.of(page, size, Sort.by(Direction.DESC, "id"));
			return bugService.getPage(paging);
	}
	
	@GetMapping("/{word}")
	public Page<Bug> searchProject(@RequestParam(name="page") int page,
	        						   @RequestParam(name="size") int size,
	        						   @PathVariable(value = "word") String word) {
		Pageable paging = PageRequest.of(page, size, Sort.by(Direction.DESC,"id"));
		return bugService.searchBug(word,paging);
	}
	
	@PostMapping("/bug")
	public Bug createBug(@RequestBody Bug bug) {
		return bugService.createBug(bug);
	}
	
	@PutMapping("/bug")
	public Bug updateBug(@RequestBody Bug bug) {
		return bugService.updateBug(bug);
	}
	
	@GetMapping("/bug/{id}")
	public Bug getBug(@PathVariable(value = "id") long id) {
		return bugService.getBugById(id);
	}

	
	@DeleteMapping("/bug/{id}")
	public void delete(@PathVariable(value = "id") long id) {
		bugService.deleteBug(id);
    }
	
	
}
