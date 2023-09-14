package com.raimis.Blogas.service;

import com.raimis.Blogas.entities.Comment;
import com.raimis.Blogas.repo.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void addCommentToTopic(Comment comment) {
        commentRepository.save(comment);
    }
}
