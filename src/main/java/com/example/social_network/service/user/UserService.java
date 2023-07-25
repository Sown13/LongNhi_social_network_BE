package com.example.social_network.service.user;

import com.example.social_network.model.user.User;
import com.example.social_network.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }
    @Override
    public Optional<User> findById(Long id) {
      return userRepo.findById(id);
    }


    @Override
    public User save(User user) throws Exception {
        if (userRepo.findUserByAccountName(user.getAccountName()).isPresent()) {
            throw new Exception("Username already exists");
        }

        if (userRepo.findUsersByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }

        return userRepo.save(user);
    }

    public User getUserByUsername(String username) throws Exception {
        Optional<User> optionalUser = userRepo.findUserByAccountName(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new Exception("User not found");
        }
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<User> findAllUsersByAccount(String account) {
        return userRepo.findUsersByAccountNameContaining(account);
    }

    @Override
    public Optional<User> findByAccountName(String accountName) {
        return userRepo.findUserByAccountName(accountName);
    }

    @Override
    public boolean checkPassword(User user, String password) {
      return  user.getPassword().equals(password);
    }


    @Override
    public User update(User user) {
        return userRepo.save(user);
    }



//    @Override
//    public Iterable<User> findAllByAccountName(String name) {
//        return userRepo.findUserByAccountName(name);
//    }

}
