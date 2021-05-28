package com.blogapp.bloagapp.data.models;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String profession;

    @Column(unique = true)// after adding a new constraints to an existing column, kindly change your ddl auto --> {create}
    //Run Your Test, and the change it back to {update}.
    private String email;

    private String phoneNumber;

    @OneToMany//
    @ToString.Exclude// To avoid duplicate the invocation of toString methods of each object
    // in the bi-directional relationship
    private List<Post> posts;

    public void addPost(Post post) {
        if (posts == null) {
            posts = new ArrayList<>();
        }
        posts.add(post);

    }

}
