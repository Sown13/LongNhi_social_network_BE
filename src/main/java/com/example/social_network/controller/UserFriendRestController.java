package com.example.social_network.controller;

import com.example.social_network.model.friend.dto.HaveBeenFriendsDTO;
import com.example.social_network.model.friend.dto.SourceUserFriendDTO;
import com.example.social_network.model.friend.dto.TargetUserFriendDTO;
import com.example.social_network.model.friend.UserFriend;
import com.example.social_network.service.friend.UserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("/user-friends")
public class UserFriendRestController {

    @Autowired
    UserFriendService userFriendService;

    @GetMapping("/")
    public ResponseEntity<Iterable<UserFriend>> findAll() {
        Iterable<UserFriend> userFriendIterable = userFriendService.findAll();
        return new ResponseEntity<>(userFriendIterable, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserFriend>> findById(@PathVariable Long id) {
        Optional<UserFriend> userFriendOptional = userFriendService.findById(id);
        if (userFriendOptional.isPresent()) {
            return new ResponseEntity<>(userFriendOptional, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/")
    public ResponseEntity<UserFriend> sendFriendRequest(@RequestBody UserFriend userFriend) {
        LocalDate date = LocalDate.now();
        Date utilDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        userFriend.setDateRequested(utilDate);
        userFriend.setAccepted(false);
        UserFriend savedUserFriend = userFriendService.save(userFriend);
        if (savedUserFriend != null) {
            return new ResponseEntity<>(savedUserFriend, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<UserFriend>> acceptFriendRequest(@PathVariable("id") Long id) {
        Optional<UserFriend> userFriendOptional = userFriendService.findById(id);
        if (userFriendOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        LocalDate date = LocalDate.now();
        Date utilDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        UserFriend userFriend = userFriendOptional.get();
        userFriend.setAccepted(true);
        userFriend.setDateAccepted(utilDate);
        userFriendService.save(userFriend);
        return ResponseEntity.ok(userFriendOptional);
    }

    // delete khỏi danh sách , userFriend
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable("id") Long id) {
        userFriendService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Lời mời s-u and false , source gửi lời mời đến target
        @GetMapping("/{id}/friend-request-sent")
    public ResponseEntity<Iterable<SourceUserFriendDTO>> sourceSendFriend(@PathVariable Long id) {
        Iterable<SourceUserFriendDTO> userFriendIterable = userFriendService.findUserFriendByUserFriendId(id);
        return new ResponseEntity<>(userFriendIterable,HttpStatus.OK);
    }

    // t-u and false , nhận  lời mời gửi đến từ s-u
    @GetMapping("/{id}/friend-request-receive")
    public ResponseEntity<Iterable<TargetUserFriendDTO>> targetSendFriend(@PathVariable Long id) {
        Iterable<TargetUserFriendDTO> userFriendIterable = userFriendService.findUserFriendByTargetUser(id);
        return new ResponseEntity<>(userFriendIterable,HttpStatus.OK);
    }


    // Query s-u || f-u and true ,  đều là bạn bè với nhau
    @GetMapping("/have-been-friend/{sourceId}/{targetId}")
    public ResponseEntity<Iterable<HaveBeenFriendsDTO>> getHaveBeenFriends(@PathVariable("targetId") Long targetId, @PathVariable("sourceId") Long sourceId) {
        Iterable<HaveBeenFriendsDTO> haveBeenFriends = userFriendService.findUserFriendByTargetUserOrSourceUser(targetId, sourceId);
        return ResponseEntity.ok(haveBeenFriends);

    }






}



