package com.epam.advanced.java.service;

import com.epam.advanced.java.common.UserRequestDto;
import com.epam.advanced.java.common.UserResponseDto;
import com.epam.advanced.java.domain.bo.User;
import com.epam.advanced.java.exception.EntityAlreadyExistsException;
import com.epam.advanced.java.exception.EntityNotFoundException;
import com.epam.advanced.java.facade.IUserService;
import com.epam.advanced.java.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserV2Service implements IUserService {

    private final UserJpaRepository userJpaRepository;
    private final ConversionService conversionService;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequest) {
        var user = conversionService.convert(userRequest, User.class);
        User createdUser = userJpaRepository.save(user);
        return conversionService.convert(createdUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDto userRequest) {
        checkIfExists(userId);
        var userToUpdate = userJpaRepository.findById(userId).get();

        userToUpdate.setName(userToUpdate.getName());
        userToUpdate.setSurname(userRequest.getSurname());
        userToUpdate.setBirthday(LocalDate.parse(userRequest.getBirthday()));
        var updatedUser = userJpaRepository.save(userToUpdate);

        return conversionService.convert(updatedUser, UserResponseDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        checkIfExists(userId);
        userJpaRepository.deleteById(userId);
    }

    @Override
    public UserResponseDto getUser(Long userId) {
        checkIfExists(userId);
        var selectedUser = userJpaRepository.findById(userId).get();
        return conversionService.convert(selectedUser, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userJpaRepository.findAll().stream()
                .map(user -> conversionService.convert(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    private void checkIfExists(Long userId) {
        if (userJpaRepository.findById(userId).isEmpty()) {
            throw new EntityNotFoundException(format("User with ID %s already exists", userId));
        }
    }

    private void checkIfNotExists(Long userId) {
        if (userJpaRepository.findById(userId).isPresent()) {
            throw new EntityAlreadyExistsException(format("User with ID %s already exists", userId));
        }
    }
}
