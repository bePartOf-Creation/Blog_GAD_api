package com.blogapp.bloagapp.service.post;

import com.blogapp.bloagapp.data.models.Comment;
import com.blogapp.bloagapp.data.models.Post;
import com.blogapp.bloagapp.data.repository.PostRepository;
import com.blogapp.bloagapp.web.dto.PostDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    @Override
    public Post savePost(PostDTO postDTO) {
        Post post = new Post();
        ModelMapper modelMap = new ModelMapper();
        modelMap.map(postDTO, post);

        log.info("Post object after mapping --> {}",post);

        return postRepository.save(post);
    }
    @Override
    public List<Post> findAllPosts() {
        return null;
    }
    @Override
    public Post updatePost(PostDTO postDTO) {
        return null;
    }
    @Override
    public Post findById(Integer id) {
        return null;
    }

    @Override
    public void deletePostById(Integer id) {

    }

    @Override
    public Post addCommentToPost(Integer id, Comment comment) {
        return null;
    }
}
