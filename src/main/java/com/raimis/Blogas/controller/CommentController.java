package com.raimis.Blogas.controller;

import com.raimis.Blogas.entities.Comment;
import com.raimis.Blogas.entities.User;
import com.raimis.Blogas.service.CommentService;
import com.raimis.Blogas.service.TopicService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final TopicService topicService;

    public CommentController(TopicService topicService, CommentService commentService) {
        this.topicService = topicService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public String addCommentToTopic(@AuthenticationPrincipal User user, @ModelAttribute Comment comment, @RequestParam long topicId) {
        topicService.addComment(topicId, comment, user);
        return "redirect:/topics/%s".formatted(comment.getTopic().getId());
    }
}
