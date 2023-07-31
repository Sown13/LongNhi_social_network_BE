package com.example.social_network.model.post;

import com.example.social_network.model.comment.Comment;
import com.example.social_network.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table()
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long postId;
    private String textContent;
    private Date dateCreated;
    private Date dateUpdated;
    private String authorizedView = "public";
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "post")
    private List<PostImage> postImageList;
    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;
    @OneToMany(mappedBy = "post")
    private List<PostReaction> postReactionList;
}
