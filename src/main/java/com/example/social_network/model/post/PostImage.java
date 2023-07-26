package com.example.social_network.model.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostImage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long postImageId;
    private String imgUrl;
    private Date dateCreated;
    @ManyToOne
    @JsonIgnore
    private Post post;
}
