package com.blogapp.bloagapp.web.dto;

import lombok.Data;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class PostDTO {

    @NotNull(message = "Title cannot be null")
    private  String title;

    @NotNull(message = "Content cannot be null")
    private String content;

    private MultipartFile coverImageUrl;// MultipartFile( used to hold the uploaded mage from the user)
}
