package com.blogapp.bloagapp.web.controllers;

import com.blogapp.bloagapp.service.post.PostService;
import com.blogapp.bloagapp.web.dto.PostDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postServiceImpl;


    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/create")
    public String getPostForm(Model model){
        model.addAttribute("post", new PostDTO());//"post" is the variable to be used in the html template,while "PostDTo" is used to accept the user data wit its information.
        return "create";
    }
    @PostMapping("/save")
    public String savePost(@ModelAttribute @Valid PostDTO postDTO){//@ModelAttribute request for data from the  "Model args" from "/create" api
        log.info("Post dto received --> {}",postDTO);
        return  "index";
    }
}
