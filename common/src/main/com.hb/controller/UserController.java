package com.hb.controller;

import com.hb.entity.UserEntity;
import com.hb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/save")
    public ResponseEntity<?> createUser(UserEntity userEntity) {
        try {
            return this.userService.saveUser(userEntity);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch")
    public List<UserEntity> getUser() {
        return this.userService.getAllUser();
    }

    @GetMapping("/fetch/{userId}")
    public UserEntity getUserById(@PathVariable Long userId) {
        return this.userService.getUserById(userId);
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        return this.userService.deleteUserById(userId);
    }

    @PutMapping("/update/{userId}")
    public String updateUser(UserEntity userEntity, Long userId) {
        return this.userService.updateUserById(userEntity, userId);
    }
}
