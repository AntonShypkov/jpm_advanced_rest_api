package com.epam.advanced.java.service;

import com.epam.advanced.java.bo.Subscription;
import com.epam.advanced.java.bo.User;
import com.epam.advanced.java.common.SubscriptionRequestDto;
import com.epam.advanced.java.common.SubscriptionResponseDto;
import com.epam.advanced.java.exception.EntityAlreadyExistsException;
import com.epam.advanced.java.exception.EntityNotFoundException;
import com.epam.advanced.java.facade.ISubscriptionService;
import com.epam.advanced.java.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SubscriptionService implements ISubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequest) {
        checkIfNotExists(subscriptionRequest.getId());

        var subscription = Subscription.builder()
                .id(subscriptionRequest.getId())
                .user(User.builder().id(subscriptionRequest.getUserId()).build())
                .startdDate(LocalDate.parse(subscriptionRequest.getStartDate()))
                .build();

        Subscription createdSubscription = subscriptionRepository.create(subscription);

        return SubscriptionResponseDto.builder()
                .id(createdSubscription.getId())
                .userId(createdSubscription.getUser().getId())
                .startDate(createdSubscription.getStartdDate().toString())
                .build();
    }

    @Override
    public SubscriptionResponseDto updateSubscription(Long subscriptionId, SubscriptionRequestDto subscriptionRequest) {
        checkIfExists(subscriptionId);

        var subscriptionToUpdate = subscriptionRepository.findById(subscriptionId);

        subscriptionToUpdate.setUser(User.builder().id(subscriptionRequest.getUserId()).build());
        subscriptionToUpdate.setStartdDate(LocalDate.parse(subscriptionRequest.getStartDate()));

        Subscription updatedSubscription = subscriptionRepository.update(subscriptionToUpdate);

        return SubscriptionResponseDto.builder()
                .id(updatedSubscription.getId())
                .userId(updatedSubscription.getUser().getId())
                .startDate(updatedSubscription.getStartdDate().toString())
                .build();
    }

    @Override
    public void deleteSubscription(Long subscriptionId) {
        checkIfExists(subscriptionId);
        subscriptionRepository.delete(subscriptionId);
    }

    @Override
    public SubscriptionResponseDto getSubscription(Long subscriptionId) {
        checkIfExists(subscriptionId);

        var subscription = subscriptionRepository.findById(subscriptionId);

        return SubscriptionResponseDto.builder()
                .id(subscription.getId())
                .userId(subscription.getUser().getId())
                .startDate(subscription.getStartdDate().toString())
                .build();
    }

    @Override
    public List<SubscriptionResponseDto> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(subscription -> SubscriptionResponseDto.builder()
                        .id(subscription.getId())
                        .userId(subscription.getUser().getId())
                        .startDate(subscription.getStartdDate().toString())
                        .build())
                .collect(Collectors.toList());
    }

    private void checkIfExists(Long subscriptionId) {
        if (subscriptionRepository.findById(subscriptionId) == null) {
            throw new EntityNotFoundException(format("Subscription with ID %s already exists", subscriptionId));
        }
    }

    private void checkIfNotExists(Long subscriptionId) {
        if (subscriptionRepository.findById(subscriptionId) != null) {
            throw new EntityAlreadyExistsException(format("Subscription with ID %s already exists", subscriptionId));
        }
    }
}
