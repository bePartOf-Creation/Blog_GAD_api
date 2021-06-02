package com.blogapp.bloagapp.service.post;

import com.blogapp.bloagapp.data.models.Post;
import com.blogapp.bloagapp.data.repository.PostRepository;
import com.blogapp.bloagapp.web.dto.PostDTO;
import com.blogapp.bloagapp.web.exceptions.PostObjectIsNullException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceImplTest {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostServiceImpl postServiceImpl = new PostServiceImpl();

    Post testPost;

    @BeforeEach
    void setUp() {
        testPost  = new Post();
        MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() {
    }
    @Test
    void whenTheSaveMethodIsCalled_thenRepositoryIsCalledOnceTest() throws PostObjectIsNullException {
        when(postServiceImpl.savePost(new PostDTO())).thenReturn(testPost);
        postServiceImpl.savePost(new PostDTO());

        verify(postRepository, times(1)).save(testPost);

    }
    @Test
    void whenTheSaveFindAllMethodIsCalled_thenReturnAListOfPostsTest() {
        List<Post> postList = new ArrayList<>();
        when(postServiceImpl.findAllPosts()).thenReturn(postList);
        postServiceImpl.findAllPosts();

        verify(postRepository, times(1)).findAll();

    }
}