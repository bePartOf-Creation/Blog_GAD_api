package com.blogapp.bloagapp.data.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Comment {

    @Id
    private Integer id;

    private String authorName;

    private LocalDate dateModified;

    @Column(nullable = false, length= 150)
    private String content;
}
