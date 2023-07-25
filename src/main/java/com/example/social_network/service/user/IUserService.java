package com.example.social_network.service.user;

import com.example.social_network.model.user.User;
import com.example.social_network.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User> {
    List<User> findAllUsersByAccount(String account);

    Optional<User> findByAccountName(String username);
     boolean checkPassword(User user, String password);
    User update( User user);


}
