package com.zahra.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zahra.app.model.Bug;



public interface BugService {

	
	List<Bug> getAll();
	
	Bug createBug(Bug bug);

	Bug getBugById(long id);
	
	Bug updateBug(Bug bug);

	void deleteBug(long id);
	
	Page<Bug> getPage(Pageable pageable);

	Page<Bug> searchBug(String word, Pageable paging);
	
	
	
}
