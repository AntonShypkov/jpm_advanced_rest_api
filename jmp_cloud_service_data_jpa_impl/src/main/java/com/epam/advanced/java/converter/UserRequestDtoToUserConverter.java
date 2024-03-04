package com.epam.advanced.java.converter;

import com.epam.advanced.java.common.UserRequestDto;
import com.epam.advanced.java.domain.bo.User;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class UserRequestDtoToUserConverter implements Converter<UserRequestDto, User> {
    @Override
    public User convert(UserRequestDto userRequest) {
        return User.builder()
                .id(userRequest.getId())
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .birthday(LocalDate.parse(userRequest.getBirthday()))
                .build();
    }
}
