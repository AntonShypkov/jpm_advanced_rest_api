package com.epam.advanced.java.controller;

import com.epam.advanced.java.common.UserRequestDto;
import com.epam.advanced.java.common.UserResponseDto;
import com.epam.advanced.java.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/study/v1/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequest){
        log.info("Create new user with data: {}", userRequest);
        return userService.createUser(userRequest);
    }

    @PutMapping("/{userId}")
    public UserResponseDto updateUser(@PathVariable Long userId, @RequestBody UserRequestDto userRequest){
        log.info("Update user with id {}", userId);
        return userService.updateUser(userId, userRequest);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        log.info("Delete user with id {}", userId);
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId){
        log.info("Get user with id {}", userId);
        return userService.getUser(userId);
    }

    @GetMapping("/all")
    public List<UserResponseDto> getAllUser(){
        log.info("Get all existent users");
        return userService.getAllUsers();
    }
}
