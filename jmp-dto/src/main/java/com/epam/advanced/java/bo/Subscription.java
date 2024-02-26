package com.epam.advanced.java.bo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Subscription {

    private Long id;
    private User user;
    private LocalDate startdDate;

}
