package com.example.social_network.model.friend;

import com.example.social_network.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "user_friend")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserFriend {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long UserFriendId;
    @ManyToOne
    private User sourceUser;
    @ManyToOne
    private User targetUser;
    private Date dateAccepted;
    private boolean isAccepted;
    private Date dateRequested;
    private String friendType;


}
