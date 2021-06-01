package com.blogapp.bloagapp.data.models;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
@Table
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Column(nullable = false, length = 50, unique=true )
    private  String title;
    @Column(length = 500)
    private String content;

    private String coverImageUrl;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Author author;

    @CreationTimestamp
    private LocalDate dateCreated;

    @UpdateTimestamp
    private LocalDate dateModified;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;


    public void addComments(Comment... comment){
        if(this.comments == null){
            this.comments = new ArrayList<>();
        }
        this.comments.addAll(Arrays.asList(comment));
        /*or
         * for(Comment c : comment){
         *    this.comments.add(c);
         *   }
         */



    }
}

