package com.example.social_network.model.friend.dto;

import lombok.Data;

@Data
public class HaveBeenFriendsDTO {

    private Long sourceUserId;
    private Long targetUserId;
    private String accountName;
    private boolean isAccepted;

    public HaveBeenFriendsDTO(Long sourceUserId, Long targetUserId, String accountName, boolean isAccepted) {
        this.sourceUserId = sourceUserId;
        this.targetUserId = targetUserId;
        this.accountName = accountName;
        this.isAccepted = isAccepted;
    }


}
