package com.blogapp.bloagapp.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue()
    private UUID id;

    private String authorName;

    private LocalDate dateModified;

    @Column(nullable = false, length= 150)
    private String content;

    public Comment(String authorName, String content){
        this.authorName = authorName;
        this.content = content;
    }


}
