package com.epam.advanced.java.converter;

import com.epam.advanced.java.common.SubscriptionResponseDto;
import com.epam.advanced.java.domain.bo.Subscription;
import org.springframework.core.convert.converter.Converter;

public class SubscriptionToSubscriptionResponseDtoConverter implements Converter<Subscription, SubscriptionResponseDto> {

    @Override
    public SubscriptionResponseDto convert(Subscription subscription) {
        return SubscriptionResponseDto.builder()
                .id(subscription.getId())
                .userId(subscription.getUser().getId())
                .startDate(subscription.getStartDate().toString())
                .build();
    }
}
