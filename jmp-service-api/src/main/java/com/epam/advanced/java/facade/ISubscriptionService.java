package com.epam.advanced.java.facade;

import com.epam.advanced.java.common.SubscriptionRequestDto;
import com.epam.advanced.java.common.SubscriptionResponseDto;

import java.util.List;

public interface ISubscriptionService {

    SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequest);

    SubscriptionResponseDto updateSubscription(Long subscriptionId, SubscriptionRequestDto subscriptionRequest);

    void deleteSubscription(Long subscriptionId);

    SubscriptionResponseDto getSubscription(Long subscriptionId);

    List<SubscriptionResponseDto> getAllSubscriptions();
}
