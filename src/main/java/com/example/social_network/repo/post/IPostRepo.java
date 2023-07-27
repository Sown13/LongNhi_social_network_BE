package com.example.social_network.repo.post;

import com.example.social_network.model.post.Post;
import com.example.social_network.model.post.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepo extends JpaRepository<Post,Long> {
    @Query("SELECT p FROM Post p WHERE p.user.userId = :userId")
    Iterable<Post> findPostsByUserId(Long userId);



    @Query(value = "select * from social_network.post join social_network.user_friend as uf \n" +
            "on post.user_user_id = uf.target_user_user_id where is_accepted = true and source_user_user_id = :id\n" +
            "union select * from social_network.post join social_network.user_friend as uf \n" +
            "on post.user_user_id = uf.source_user_user_id where is_accepted = true and target_user_user_id = :id" ,nativeQuery = true)
    Iterable<Post> findPostsOfAcceptedFriends(Long id);
}