package com.epam.advanced.java.controller.v2;

import com.epam.advanced.java.common.UserRequestDto;
import com.epam.advanced.java.common.UserResponseDto;
import com.epam.advanced.java.service.UserV2Service;
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
@RequestMapping("/study/v2/user")
@Slf4j
public class UserV2Controller {

    private final UserV2Service userService;

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequest){
        log.info("V2: Create new user with data: {}", userRequest);
        var response = userService.createUser(userRequest);

        log.info("V2: Response {}", response);
        return response;
    }

    @PutMapping("/{userId}")
    public UserResponseDto updateUser(@PathVariable Long userId, @RequestBody UserRequestDto userRequest){
        log.info("V2: Update user with id {}", userId);
        var response = userService.updateUser(userId, userRequest);

        log.info("V2: Response {}", response);
        return response;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        log.info("V2: Delete user with id {}", userId);
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId){
        log.info("V2: Get user with id {}", userId);
        var response = userService.getUser(userId);

        log.info("V2: Response {}", response);
        return response;
    }

    @GetMapping("/all")
    public List<UserResponseDto> getAllUser(){
        log.info("V2: Get all existent users");
        var response = userService.getAllUsers();

        log.info("V2: Response {}", response);
        return response;
    }
}
