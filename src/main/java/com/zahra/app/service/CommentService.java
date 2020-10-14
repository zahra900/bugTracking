package com.zahra.app.service;

import java.util.List;

import com.zahra.app.model.Comment;

public interface CommentService {

	List<Comment> getAll();

	Comment createComment(Comment comment);

	List<Comment> getCommentsByBug(long id);

	void deleteComment(long id);

}
