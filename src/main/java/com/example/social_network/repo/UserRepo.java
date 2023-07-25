package com.example.social_network.repo;

import com.example.social_network.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByAccountName(String accountName);

    Optional<User> findUsersByEmail(String email);

    List<User> findUsersByAccountNameContaining(String account);

//    @Query(value = "select u from User as u where u.accountName like %:name%")
//    Iterable<User> findAllByAccountName(String name);
}
