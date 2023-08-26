package com.raimis.Blogas.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.stream.events.Comment;

@Controller
@RequestMapping("/comments") //kokiu keliu sis kontroleris bus pasiekiamas
public class CommentController {

    @GetMapping("/add")
    public String getAddCommentForm(Model model) {
       // model.addAttribute("newComment", new Comment());
        return "addComment";
    }

}

