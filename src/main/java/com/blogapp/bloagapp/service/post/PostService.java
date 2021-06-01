package com.blogapp.bloagapp.service.post;

import com.blogapp.bloagapp.data.models.Comment;
import com.blogapp.bloagapp.data.models.Post;
import com.blogapp.bloagapp.web.dto.PostDTO;

import java.util.List;

public interface PostService {
    Post savePost(PostDTO postDTO);
    List<Post> findAllPosts();
    Post updatePost(PostDTO postDTO);
    Post findById(Integer id);
    void deletePostById(Integer id);
    Post addCommentToPost(Integer id, Comment comment);
}
