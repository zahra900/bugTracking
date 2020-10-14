package com.zahra.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zahra.app.model.Bug;
import com.zahra.app.model.Project;
import com.zahra.app.model.User;
import com.zahra.app.repository.BugRepository;

@Service
public class BugServiceImpl implements BugService {

	@Autowired
	private BugRepository bugRepository;
	
	@Autowired
	private ProjectServiceImpl projectServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	public Bug createBug(Bug bug) {
		
		String currentPrincipalName = userServiceImpl.getCurrentUser();
		User assigner = userServiceImpl.findByEmail(currentPrincipalName);
		
		bug.setAssigner(assigner);
		User assignee = userServiceImpl.findByEmail(bug.getAssignee().getEmail());
		
		bug.setAssignee(assignee);
		Project project = projectServiceImpl.getProject(bug.getProject().getId());
		bug.setProject(project);
		
		return bugRepository.save(bug);
	}
	
	public List<Bug> getAll(){
		return bugRepository.findAll();
	}

	@Override
	public Bug getBugById(long id) {
		
	    Optional<Bug> optionalBug = bugRepository.findById(id);
	    Bug bug = optionalBug.isPresent() ? optionalBug.get() : null;
		return bug;
	}

	@Override
	public void deleteBug(long id) {
		bugRepository.deleteById(id);
	}
	
	@Override
	public Page<Bug> getPage(Pageable paging) {
		return bugRepository.findAll(paging);
	}

	@Override
	public Bug updateBug(Bug bug) {	
		Bug modifiedBug = getBugById(bug.getId());
		bug.setAssigner(modifiedBug.getAssigner());
		bug.setAssignee(userServiceImpl.findByEmail(bug.getAssignee().getEmail()));
		bug.setProject(projectServiceImpl.getProject(bug.getProject().getId()));
		BeanUtils.copyProperties(bug, modifiedBug);
		return bugRepository.save(modifiedBug);
	}

	@Override
	public Page<Bug> searchBug(String word, Pageable paging) {
		return bugRepository.findByTitleContaining(word,paging);
	}
}
