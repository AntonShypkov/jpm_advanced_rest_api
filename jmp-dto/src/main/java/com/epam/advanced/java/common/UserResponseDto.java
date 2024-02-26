package com.epam.advanced.java.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String birthday;

}
