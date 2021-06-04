package com.blogapp.bloagapp.web.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
public class PostDTO {

    @NotEmpty(message = "Title cannot be Empty")
    private  String title;

    @NotEmpty(message = "Content cannot be Empty")
    private String content;

    private MultipartFile coverImageUrl;// MultipartFile( used to hold the uploaded mage from the user)
}
