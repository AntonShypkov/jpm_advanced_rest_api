package com.epam.advanced.java.facade;

import com.epam.advanced.java.common.UserRequestDto;
import com.epam.advanced.java.common.UserResponseDto;

import java.util.List;

public interface IUserService {

    UserResponseDto createUser(UserRequestDto userRequest);

    UserResponseDto updateUser(Long userId, UserRequestDto userRequest);

    void deleteUser(Long userId);

    UserResponseDto getUser(Long userId);

    List<UserResponseDto> getAllUsers();
}
