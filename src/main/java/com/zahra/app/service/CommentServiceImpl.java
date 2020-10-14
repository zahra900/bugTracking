package com.zahra.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zahra.app.model.Bug;
import com.zahra.app.model.Comment;
import com.zahra.app.model.User;
import com.zahra.app.repository.CommentRepository;


@Service
public class CommentServiceImpl implements CommentService {
   
	@Autowired
	private CommentRepository commentRepository;
    
	@Autowired
	private BugService bugService;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public List<Comment> getAll() {
		List<Comment> comments = commentRepository.findByOrderByIdDesc();	
		return comments;
	}

	
	 
	@Override
	public Comment createComment(Comment comment) {
		
		String commenterEmail = userService.getCurrentUser();
		User commenter = userService.findByEmail(commenterEmail);
		comment.setCommenter(commenter);
		
		Bug bug = bugService.getBugById(comment.getBug().getId());
		comment.setBug(bug);
		
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> getCommentsByBug(long id) {
		Bug bug = bugService.getBugById(id);
		return commentRepository.findByBug(bug);
	}

	@Override
	public void deleteComment(long id) {
		commentRepository.deleteById(id);
	}
		
}
