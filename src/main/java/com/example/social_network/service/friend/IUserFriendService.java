package com.example.social_network.service.friend;

import com.example.social_network.model.friend.dto.HaveBeenFriendsDTO;
import com.example.social_network.model.friend.dto.SourceUserFriendDTO;
import com.example.social_network.model.friend.dto.TargetUserFriendDTO;
import com.example.social_network.model.friend.UserFriend;
import com.example.social_network.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserFriendService extends IGeneralService<UserFriend> {
    Iterable<UserFriend> findAllFriendsByUserId(Long id);
    Long countFriend(Long id);

   List<SourceUserFriendDTO> findUserFriendByUserFriendId(Long id);

    List<TargetUserFriendDTO> findUserFriendByTargetUser(Long id);

    List<HaveBeenFriendsDTO> findUserFriendByTargetUserOrSourceUser(@Param("targetId") Long targetId, @Param("sourceId") Long sourceId);


}
