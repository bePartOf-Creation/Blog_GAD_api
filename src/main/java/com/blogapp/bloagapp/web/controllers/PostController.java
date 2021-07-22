package com.blogapp.bloagapp.web.controllers;

import com.blogapp.bloagapp.data.models.Post;
import com.blogapp.bloagapp.service.post.PostService;
import com.blogapp.bloagapp.web.dto.PostDTO;
import com.blogapp.bloagapp.web.exceptions.PostDoesNotFoundException;
import com.blogapp.bloagapp.web.exceptions.PostObjectIsNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("")
public class PostController {

    @Autowired
    PostService postServiceImpl;


    @GetMapping("/posts")
    public String getIndex(Model model){
        List<Post> postList = postServiceImpl.findPostInDescOrder();
        model.addAttribute("postList",postList);
        return "index";
    }
    @GetMapping("/posts/single/{id}")
    public String continueReading(@PathVariable Integer id, Model model) {
        log.info("Request for a post Path --> {}", id);
       try{
           Post postDetails = postServiceImpl.findById(id);
           model.addAttribute("postInfo", postDetails);
       }catch(PostDoesNotFoundException px){
           log.info("Exception Occurred");
       }
        return "post";
    }
    @GetMapping("/create")
    public String getPostForm(Model model){
//        model.addAttribute("post", new PostDTO());//"post" is the variable to be used in the html template,while "PostDTo" is used to accept the user data wit its information.
        model.addAttribute("error",false);
        return "/create";
    }
    @PostMapping("/save")
    public String savePost(@ModelAttribute("post") @Valid PostDTO postDTO,
                           BindingResult result, Model model){//@ModelAttribute request for data from the  "Model args" from "/create" api
        log.info("Post dto received --> {}",postDTO);
        if(result.hasErrors()){
            return "create";
        }
        try{
            postServiceImpl.savePost(postDTO);
        }catch(PostObjectIsNullException e) {
            log.info("Exception occurred --> {}", e.getMessage());
        }catch (DataIntegrityViolationException dx){
            model.addAttribute("error",true);// when duplicate title occur, show this text
            model.addAttribute("errorMessage","Title Not Excepted, Already Exist");// when duplicate title occurs, show this text{customized message}
//            model.addAttribute("postDto",new PostDTO());//when duplicate title occurs,return back the form wit empty PostDTo
           return "create";
        }
        return  "redirect:/posts";
    }

    @ModelAttribute
    public void createAPostModel(Model model){
        model.addAttribute("post", new PostDTO());
    }
}
