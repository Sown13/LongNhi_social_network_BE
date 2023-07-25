package com.example.social_network.service.friend;

import com.example.social_network.model.friend.dto.HaveBeenFriendsDTO;
import com.example.social_network.model.friend.dto.SourceUserFriendDTO;
import com.example.social_network.model.friend.dto.TargetUserFriendDTO;
import com.example.social_network.model.friend.UserFriend;
import com.example.social_network.repo.UserFriendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserFriendService implements IUserFriendService {

    @Autowired
    UserFriendRepo userFriendRepo;

    @Override
    public Iterable<UserFriend> findAll() {
        return userFriendRepo.findAll();
    }

    @Override
    public Optional<UserFriend> findById(Long id) {
        return userFriendRepo.findById(id);
    }

    @Override
    public UserFriend save(UserFriend userFriend) {
        return userFriendRepo.save(userFriend);
    }

    @Override
    public void remove(Long id) {
        userFriendRepo.deleteById(id);
    }

    @Override
    public Iterable<UserFriend> findAllFriendsByUserId(Long id) {
        return userFriendRepo.findUserFriendsByUserId(id);
    }

    @Override
    public Long countFriend(Long id) {
        return userFriendRepo.countAllByUserFriend(id);
    }

    @Override
    public List<SourceUserFriendDTO> findUserFriendByUserFriendId(Long id) {
        List<Object[]> results = userFriendRepo.findUserFriendByUserFriendId(id);
        List<SourceUserFriendDTO> userFriends = new ArrayList<>();

        for (Object[] result : results) {
            Long sourceUserId = (Long) result[0];
            Long targetUserId = (Long) result[1];
            String accountName = (String) result[2];
            boolean isAccepted = (Boolean) result[3];
            userFriends.add(new SourceUserFriendDTO(sourceUserId, targetUserId, accountName, isAccepted));
        }

        return userFriends;
    }

    @Override
    public List<TargetUserFriendDTO> findUserFriendByTargetUser(Long id) {
        List<Object[]> targetResults = userFriendRepo.findUserFriendByTargetUser(id);
        List<TargetUserFriendDTO> targetUserFriends = new ArrayList<>();

        for (Object[] targetResult : targetResults) {
            Long sourceUserId = (Long) targetResult[0];
            Long targetUserId = (Long) targetResult[1];
            String accountName = (String) targetResult[2];
            boolean isAccepted = (Boolean) targetResult[3];
            targetUserFriends.add(new TargetUserFriendDTO(sourceUserId, targetUserId, accountName, isAccepted));
        }

        return targetUserFriends;
    }

    @Override
    public List<HaveBeenFriendsDTO> findUserFriendByTargetUserOrSourceUser(Long targetId, Long sourceId) {
        List<Object[]> targetResults = userFriendRepo.findUserFriendByTargetUserOrSourceUser(targetId, sourceId);
        List<HaveBeenFriendsDTO> haveBeenFriendsDTOS = new ArrayList<>();

        for (Object[] haveBeenFriendResult : targetResults) {
            Long sourceUserId = (Long) haveBeenFriendResult[0];
            Long targetUserId = (Long) haveBeenFriendResult[1];
            String accountName = (String) haveBeenFriendResult[2];
            boolean isAccepted = (Boolean) haveBeenFriendResult[3];
            haveBeenFriendsDTOS.add(new HaveBeenFriendsDTO(sourceUserId, targetUserId, accountName, isAccepted));
        }

        return haveBeenFriendsDTOS;
    }




}
