package com.blogapp.bloagapp.data.repository;

import com.blogapp.bloagapp.data.models.Author;
import com.blogapp.bloagapp.data.models.Comment;
import com.blogapp.bloagapp.data.models.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
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

        log.info("Created a Blog Post --> {}", blogPost);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void savePostToBeThere() {
        Post blogPost = new Post();
        blogPost.setTitle("what is FinTech?");
        blogPost.setContent("Lorem Ipsum Is a dummy Text");

        log.info("Created a Blog Post --> {}", blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();
    }

    @Test
    void throwExceptionWhenSavingPostWithDuplicatesValues() {
        Post blogPost = new Post();
        blogPost.setTitle("what is FinTech?");
        blogPost.setContent("Lorem Ipsum Is a dummy Text");

        log.info("Created a Blog Post --> {}", blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();

        Post blogPost2 = new Post();
        blogPost2.setTitle("what is FinTech?");
        blogPost2.setContent("Lorem Ipsum Is a dummy Text");

        log.info("Created a Blog Post --> {}", blogPost2);
        assertThrows(DataIntegrityViolationException.class, () -> postRepository.save(blogPost2));
    }

    @Test
    void whenPostIsSaved_thenSaveTheAuthor() {
        Post blogPost = new Post();
        blogPost.setTitle("what is FinTech?");
        blogPost.setContent("Lorem Ipsum Is a dummy Text");

        log.info("Created a Blog Post --> {}", blogPost);

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
    void findAllPostInTheDbTest() {
        List<Post> existingPosts = postRepository.findAll();
        assertThat(existingPosts).isNotNull();
        assertEquals(5, existingPosts.size());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void deletePostTest() {
        Post savedPost = postRepository.findById(41).orElse(null);
        assertThat(savedPost).isNotNull();
        log.info("Post fetched from the database --> {}", savedPost);

        //delete  a post
        postRepository.deleteById(savedPost.getId());

        Post deletedPost = postRepository.findById(savedPost.getId()).orElse(null);
        assertThat(deletedPost).isNull();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updateSavedPostTest() {
        Post savedPost = postRepository.findById(41).orElse(null);
        assertThat(savedPost).isNotNull();
        assertEquals("Title Post 1", savedPost.getTitle());
        assertEquals("Post content 1", savedPost.getContent());
        log.info("Post fetched from the database --> {}", savedPost);
        // Update your post
        savedPost.setTitle("Who is God?");
        savedPost.setContent("God IS Almighty");

        postRepository.save(savedPost);
        assertEquals("Who is God?", savedPost.getTitle());
        assertEquals("God IS Almighty", savedPost.getContent());
        // Confirm Updated Post
        Post updatedPost = postRepository.findById(savedPost.getId()).orElse(null);
        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getTitle()).isEqualTo("Who is God?");
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updatePostAuthorTest() {
        Post savedPost = postRepository.findById(41).orElse(null);
        assertThat(savedPost).isNotNull();
        assertThat(savedPost.getAuthor()).isEqualTo(null);
        log.info("Post fetched from the database --> {}", savedPost);

        //Create an Author
        Author author = new Author();
        author.setFirstName("Brown");
        author.setLastName("Blue");
        author.setPhoneNumber("09078243567");
        author.setProfession("Musician");
        author.setEmail("brown@gmail.com");

        //set your author and save to database
        savedPost.setAuthor(author);
        postRepository.save(savedPost);


        assertThat(savedPost.getAuthor()).isNotNull();
        assertThat(savedPost.getAuthor().getFirstName()).isEqualTo("Brown");
        assertThat(savedPost.getAuthor().getLastName()).isEqualTo("Blue");
        assertThat(savedPost.getAuthor().getPhoneNumber()).isEqualTo("09078243567");
        assertThat(savedPost.getAuthor().getProfession()).isEqualTo("Musician");
        log.info("Post Fetched  Has ---> {}", savedPost);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updatedPostWithAComment() {
        Post savedPost = postRepository.findById(41).orElse(null);
        assertThat(savedPost).isNotNull();
        assertThat(savedPost.getAuthor()).isEqualTo(null);
        assertThat(savedPost.getComments().size()).isEqualTo(0);
        log.info("Post fetched from the database --> {}", savedPost);

        Author author = new Author();
        author.setFirstName("Brown");
        author.setLastName("Blue");
        author.setPhoneNumber("09078243567");
        author.setProfession("Musician");
        author.setEmail("brown@gmail.com");

        //set your author and save to database
        savedPost.setAuthor(author);
        postRepository.save(savedPost);

        assertThat(savedPost.getAuthor()).isNotNull();
        assertThat(savedPost.getAuthor().getFirstName()).isEqualTo("Brown");
        assertThat(savedPost.getAuthor().getLastName()).isEqualTo("Blue");
        assertThat(savedPost.getAuthor().getPhoneNumber()).isEqualTo("09078243567");
        assertThat(savedPost.getAuthor().getProfession()).isEqualTo("Musician");
        log.info("Post Fetched  Has ---> {}", savedPost);

        Comment comment1 = new Comment("Kola", "Good-Content");
        Comment comment2 = new Comment("KCore", "Nice One Baby Core");
        //map the post and comments
        savedPost.addComments(comment1, comment2);
        //save the post
        postRepository.save(savedPost);


        Post commentedPost = postRepository.findById(savedPost.getId()).orElse(null);
        assertThat(commentedPost).isNotNull();
        assertThat(commentedPost.getComments().size()).isEqualTo(2);
        assertThat(commentedPost.getComments().get(0).getAuthorName()).isEqualTo("Kola");
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void findALlPostInDescendingOrderTest() {
        List<Post> allPosts = postRepository.findByOrderByDateCreatedDesc();
        assertThat(allPosts).isNotEmpty();
        log.info("ALl Post --> {}", allPosts);
        assertTrue(allPosts.get(0).getDateCreated().isAfter(allPosts.get(1).getDateCreated()));
        allPosts.forEach(post -> {
            log.info("Post Date --> {}", post.getDateCreated());
        });


    }

}