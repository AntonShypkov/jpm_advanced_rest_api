package com.epam.advanced.java.converter;

import com.epam.advanced.java.common.UserResponseDto;
import com.epam.advanced.java.domain.bo.User;
import org.springframework.core.convert.converter.Converter;

public class UserToUserResponseDtoConverter implements Converter<User, UserResponseDto> {

    @Override
    public UserResponseDto convert(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .birthday(user.getBirthday().toString())
                .build();
    }
}
