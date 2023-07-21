package com.example.social_network.controller;

import com.example.social_network.model.user.User;
import com.example.social_network.service.user.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private IUserService userService;

    @GetMapping()
    private ResponseEntity<Iterable<User>> findAllUsers() {
        Iterable<User> listUser = userService.findAll();
        return new ResponseEntity<>(listUser, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<User>> findUserByid(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/name")
    private ResponseEntity<Iterable<User>> findUsersByAccountNameContaining(@RequestParam("name") String name) {
        Iterable<User> listUser = userService.findAllUsersByAccount(name);
        return ResponseEntity.ok(listUser);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        try {
            userService.save(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/update-password/{id}")
    private ResponseEntity<?>updatePassword(@PathVariable ("id") Long id,@RequestParam("password") String password,@RequestParam("new-password") String newPassword,
    @RequestParam ("check-new-password") String checkNewPassword){
        Optional<User>optionalUser=userService.findById(id);
        if(optionalUser.isPresent()){
            if(!userService.checkPassword(password)){
                if(newPassword.equals(checkNewPassword)){
                    optionalUser.get().setPassword(checkNewPassword);
                    userService.update(optionalUser.get());
                    return new ResponseEntity<>(optionalUser.get(),HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PutMapping("/{id}")
    private ResponseEntity<?> updateUser(@Valid @PathVariable("id") Long id, @RequestBody User user, BindingResult result) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            user.setUserId(id);
            if (result.hasErrors()) {
                return ResponseEntity.badRequest().body(user);
            }
            try {
                userService.update(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

