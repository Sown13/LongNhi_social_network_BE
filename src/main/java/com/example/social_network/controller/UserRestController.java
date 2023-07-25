package com.example.social_network.controller;

import com.example.social_network.model.user.User;
import com.example.social_network.model.user.dto.UserDTO;
import com.example.social_network.service.user.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    @GetMapping("/search")
    private ResponseEntity<List<User>> findUsersByAccountNameContaining(@RequestParam("name") String name) {
        List<User> listUser = userService.findAllUsersByAccount(name);
         if(!listUser.isEmpty()){
          return new ResponseEntity<>(listUser,HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

@PostMapping("/register")
public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        List<String> errors = bindingResult.getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }
    try {
        Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        user.setCreatedDate(now);
        userService.save(user);
        return ResponseEntity.ok("User registered successfully");
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

    @PostMapping("/update/{id}")
    private ResponseEntity<?> testUpdatePassword(@Valid @PathVariable("id") Long id, @RequestBody @Validated UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        User user = userService.findById(id).orElse(null);
        if (user != null) {
            if (userService.checkPassword(user, userDTO.getPassword())) {
                user.setPassword(userDTO.getNewPassword());
                userService.update(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/update-is-block/{id}")
    private ResponseEntity<?>isBlockUser(@PathVariable("id")Long id){
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setBlock(!user.isBlock());
            userService.update(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

