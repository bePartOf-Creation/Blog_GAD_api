package com.blogapp.bloagapp.data.repository;

import com.blogapp.bloagapp.data.models.Author;
import com.blogapp.bloagapp.data.models.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;


import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})

class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
        Post blogPost = new Post();
        blogPost.setTitle("what is FinTech?");
        blogPost.setContent("Lorem Ipsum Is a dummy Text");

        log.info("Created a Blog Post --> {}",blogPost);

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void savePostToBeThere(){
        Post blogPost = new Post();
        blogPost.setTitle("what is FinTech?");
        blogPost.setContent("Lorem Ipsum Is a dummy Text");

        log.info("Created a Blog Post --> {}",blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();
    }

    @Test
    void throwExceptionWhenSavingPostWithDuplicatesValues(){
        Post blogPost = new Post();
        blogPost.setTitle("what is FinTech?");
        blogPost.setContent("Lorem Ipsum Is a dummy Text");

        log.info("Created a Blog Post --> {}",blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();

        Post blogPost2 = new Post();
        blogPost2.setTitle("what is FinTech?");
        blogPost2.setContent("Lorem Ipsum Is a dummy Text");

        log.info("Created a Blog Post --> {}",blogPost2);
        assertThrows(DataIntegrityViolationException.class,() -> postRepository.save(blogPost2));
    }

    @Test
     void whenPostIsSaved_thenSaveTheAuthor(){
        Post blogPost = new Post();
        blogPost.setTitle("what is FinTech?");
        blogPost.setContent("Lorem Ipsum Is a dummy Text");

        log.info("Created a Blog Post --> {}",blogPost);

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Wick");
        author.setEmail("john@gamil.com");
        author.setPhoneNumber("2028373839");

        blogPost.setAuthor(author);
        author.addPost(blogPost);

        postRepository.save(blogPost);
        log.info("BlogPost post After saving --> {}", blogPost);
    }

    @Test
    void findAllPostInTheDbTest(){
        List<Post> existingPosts = postRepository.findAll();
        assertThat(existingPosts).isNotNull();
        assertEquals(5,existingPosts.size());

    }
}