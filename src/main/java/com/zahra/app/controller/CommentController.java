package com.zahra.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zahra.app.model.Comment;
import com.zahra.app.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService  commentService;
	
	@GetMapping
	public List<Comment> getComments() {
		return commentService.getAll();
	}
	
	@PostMapping("/comment")
	public Comment createComment(@RequestBody Comment comment) {
		return commentService.createComment(comment);
	}
	
	@GetMapping("/comment/{id}")
	public List<Comment> getComment(@PathVariable(value = "id") long id) {
		return commentService.getCommentsByBug(id);
	}
	
	@DeleteMapping("/comment/{id}")
	public void delete(@PathVariable(value = "id") long id) {
		commentService.deleteComment(id);
    }
	
	
}
