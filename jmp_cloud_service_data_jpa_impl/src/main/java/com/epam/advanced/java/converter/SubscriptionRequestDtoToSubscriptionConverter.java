package com.epam.advanced.java.converter;

import com.epam.advanced.java.common.SubscriptionRequestDto;
import com.epam.advanced.java.domain.bo.Subscription;
import com.epam.advanced.java.domain.bo.User;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class SubscriptionRequestDtoToSubscriptionConverter implements Converter<SubscriptionRequestDto, Subscription> {
    @Override
    public Subscription convert(SubscriptionRequestDto subscriptionRequest) {
        return Subscription.builder()
                .id(subscriptionRequest.getId())
                .user(User.builder().id(subscriptionRequest.getUserId()).build())
                .startDate(LocalDate.parse(subscriptionRequest.getStartDate()))
                .build();
    }
}
