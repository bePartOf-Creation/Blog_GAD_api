package com.blogapp.bloagapp.data.repository;

import com.blogapp.bloagapp.data.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {


    Post findByTitle(String title);

    @Query("select p from Post p where p.title =:title")
    Post findByPostTitle(String title);

    List<Post> findByOrderByDateCreatedDesc();
}
