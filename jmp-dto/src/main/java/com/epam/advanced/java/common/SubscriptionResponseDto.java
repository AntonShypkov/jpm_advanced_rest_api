package com.epam.advanced.java.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionResponseDto {

    private Long id;
    private Long userId;
    private String startDate;
}
