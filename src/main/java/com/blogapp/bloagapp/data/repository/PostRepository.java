package com.blogapp.bloagapp.data.repository;

import com.blogapp.bloagapp.data.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {


    Post findByTitle(String title);

    @Query("select p from Post p where p.title =:title")
    Post findByPostTitle(String title);

}
