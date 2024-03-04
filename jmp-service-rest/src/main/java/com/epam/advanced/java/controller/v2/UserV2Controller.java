package com.epam.advanced.java.controller.v2;

import com.epam.advanced.java.common.UserRequestDto;
import com.epam.advanced.java.common.UserResponseDto;
import com.epam.advanced.java.service.UserV2Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@Api(tags = {"V2 User Service (database storage)"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/study/v2/user")
@Slf4j
public class UserV2Controller {

    private final UserV2Service userService;

    @ApiOperation("Creates new user")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Created new user")
    })
    @PostMapping
    public UserResponseDto createUser(@ApiParam("Request with user data") @RequestBody UserRequestDto userRequest){
        log.info("V2: Create new user with data: {}", userRequest);
        var response = userService.createUser(userRequest);

        log.info("V2: Response {}", response);
        return response;
    }

    @ApiOperation("Updates existent user")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Updated existent user")
    })
    @PutMapping("/{userId}")
    public UserResponseDto updateUser( @ApiParam("User ID to be updated") @PathVariable Long userId,
                                       @ApiParam("User update data") @RequestBody UserRequestDto userRequest){
        log.info("V2: Update user with id {}", userId);
        var response = userService.updateUser(userId, userRequest);

        log.info("V2: Response {}", response);
        return response;
    }

    @ApiOperation("Removes existent user by ID")
    @DeleteMapping("/{userId}")
    public void deleteUser(@ApiParam("User ID to be removed") @PathVariable Long userId){
        log.info("V2: Delete user with id {}", userId);
        userService.deleteUser(userId);
    }

    @ApiOperation("Extracts subscriptions with provided ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returned subscriptions with provided ID"),
            @ApiResponse(code = 404, message = "Subscription with provided ID not found")
    })
    @GetMapping("/{userId}")
    public UserResponseDto getUser(@ApiParam("User ID to be selected")@PathVariable Long userId){
        log.info("V2: Get user with id {}", userId);
        var response = userService.getUser(userId);

        log.info("V2: Response {}", response);
        return response;
    }

    @ApiOperation("Extracts all existent subscriptions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returned all existent subscriptions")
    })
    @GetMapping("/all")
    public List<UserResponseDto> getAllUser(){
        log.info("V2: Get all existent users");
        var response = userService.getAllUsers();

        log.info("V2: Response {}", response);
        return response;
    }
}
