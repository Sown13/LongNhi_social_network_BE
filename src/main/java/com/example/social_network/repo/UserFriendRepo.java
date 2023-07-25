package com.example.social_network.repo;

import com.example.social_network.model.friend.UserFriend;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@PersistenceContext

@Repository
public interface UserFriendRepo extends JpaRepository<UserFriend, Long> {
    @Query("SELECT COUNT(*) FROM UserFriend u WHERE u.isAccepted = true AND u.targetUser = :userId")
    Long countAllByUserFriend(@Param("userId") Long userId);

    @Query("SELECT uf.targetUser FROM UserFriend uf WHERE uf.sourceUser.userId = :id and uf.isAccepted = true")
    Iterable<UserFriend> findUserFriendsByUserId(Long id);

    @Query(value = "SELECT user_friend.source_user_user_id, user_friend.target_user_user_id, users.account_name, user_friend.is_accepted " +
            "FROM user_friend " +
            "JOIN users ON users.user_id = user_friend.target_user_user_id " +
            "WHERE user_friend.source_user_user_id = ?1 AND user_friend.is_accepted = false", nativeQuery = true)
    List<Object[]> findUserFriendByUserFriendId(Long id);


    @Query(value = "select user_friend.source_user_user_id , user_friend.target_user_user_id , users.account_name , user_friend.is_accepted from user_friend\n" +
            "join users on users.user_id = user_friend.source_user_user_id\n" +
            "where user_friend.target_user_user_id = :id and user_friend.is_accepted = false", nativeQuery = true)
    List<Object[]> findUserFriendByTargetUser(Long id);


    @Query(value = "select user_friend.source_user_user_id , user_friend.target_user_user_id , users.account_name , user_friend.is_accepted from user_friend\n" +
            "join users on users.user_id = user_friend.source_user_user_id\n" +
            "where (user_friend.target_user_user_id = :targetId or user_friend.source_user_user_id = :sourceId) and user_friend.is_accepted = true", nativeQuery = true)
    List<Object[]> findUserFriendByTargetUserOrSourceUser(@Param("targetId") Long targetId, @Param("sourceId") Long sourceId);




}
