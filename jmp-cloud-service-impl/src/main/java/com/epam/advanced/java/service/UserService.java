package com.epam.advanced.java.service;

import com.epam.advanced.java.bo.User;
import com.epam.advanced.java.common.UserRequestDto;
import com.epam.advanced.java.common.UserResponseDto;
import com.epam.advanced.java.exception.EntityAlreadyExistsException;
import com.epam.advanced.java.exception.EntityNotFoundException;
import com.epam.advanced.java.facade.IUserService;
import com.epam.advanced.java.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequest) {
        checkIfNotExists(userRequest.getId());

        var user = User.builder()
                .id(userRequest.getId())
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .birthday(LocalDate.parse(userRequest.getBirthday()))
                .build();

        User createdUser = userRepository.create(user);

        return UserResponseDto.builder()
                .id(createdUser.getId())
                .name(createdUser.getName())
                .surname(createdUser.getSurname())
                .birthday(createdUser.getBirthday().toString())
                .build();
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDto userRequest) {
        checkIfExists(userId);

        var userToUpdate = userRepository.findById(userId);

        userToUpdate.setName(userToUpdate.getName());
        userToUpdate.setSurname(userRequest.getSurname());
        userToUpdate.setBirthday(LocalDate.parse(userRequest.getBirthday()));

        var updatedUser = userRepository.update(userToUpdate);

        return UserResponseDto.builder()
                .id(updatedUser.getId())
                .name(updatedUser.getName())
                .surname(updatedUser.getSurname())
                .birthday(updatedUser.getBirthday().toString())
                .build();
    }

    @Override
    public void deleteUser(Long userId) {
        checkIfExists(userId);
        userRepository.delete(userId);
    }

    @Override
    public UserResponseDto getUser(Long userId) {
        checkIfExists(userId);

        var selectedUser = userRepository.findById(userId);

        return UserResponseDto.builder()
                .id(selectedUser.getId())
                .name(selectedUser.getName())
                .surname(selectedUser.getSurname())
                .birthday(selectedUser.getBirthday().toString())
                .build();
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> UserResponseDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .surname(user.getSurname())
                        .birthday(user.getBirthday().toString())
                        .build())
                .collect(Collectors.toList());
    }

    private void checkIfExists(Long userId) {
        if (userRepository.findById(userId) == null) {
            throw new EntityNotFoundException(format("User with ID %s already exists", userId));
        }
    }

    private void checkIfNotExists(Long userId) {
        if (userRepository.findById(userId) != null) {
            throw new EntityAlreadyExistsException(format("User with ID %s already exists", userId));
        }
    }
}
