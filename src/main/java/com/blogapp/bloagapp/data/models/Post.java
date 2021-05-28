package com.blogapp.bloagapp.data.models;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
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

    @OneToMany
    private List<Comment> comment;
}

