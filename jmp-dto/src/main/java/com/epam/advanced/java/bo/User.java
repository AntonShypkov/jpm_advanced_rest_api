package com.epam.advanced.java.bo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User {

    private Long id;
    private String name;
    private String surname;
    private LocalDate birthday;

}
