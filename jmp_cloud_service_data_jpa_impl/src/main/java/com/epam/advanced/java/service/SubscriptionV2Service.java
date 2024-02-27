package com.epam.advanced.java.service;

import com.epam.advanced.java.common.SubscriptionRequestDto;
import com.epam.advanced.java.common.SubscriptionResponseDto;
import com.epam.advanced.java.domain.bo.Subscription;
import com.epam.advanced.java.domain.bo.User;
import com.epam.advanced.java.exception.EntityAlreadyExistsException;
import com.epam.advanced.java.exception.EntityNotFoundException;
import com.epam.advanced.java.facade.ISubscriptionService;
import com.epam.advanced.java.repository.SubscriptionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SubscriptionV2Service implements ISubscriptionService {

    private final SubscriptionJpaRepository subscriptionJpaRepository;
    private final ConversionService conversionService;

    @Override
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequest) {
        var subscription = conversionService.convert(subscriptionRequest, Subscription.class);
        Subscription createdSubscription = subscriptionJpaRepository.save(subscription);
        return conversionService.convert(createdSubscription, SubscriptionResponseDto.class);
    }

    @Override
    public SubscriptionResponseDto updateSubscription(Long subscriptionId, SubscriptionRequestDto subscriptionRequest) {
        checkIfExists(subscriptionId);
        var subscriptionToUpdate = subscriptionJpaRepository.findById(subscriptionId).get();

        subscriptionToUpdate.setUser(User.builder().id(subscriptionRequest.getUserId()).build());
        subscriptionToUpdate.setStartDate(LocalDate.parse(subscriptionRequest.getStartDate()));
        Subscription updatedSubscription = subscriptionJpaRepository.save(subscriptionToUpdate);

        return conversionService.convert(updatedSubscription, SubscriptionResponseDto.class);
    }

    @Override
    public void deleteSubscription(Long subscriptionId) {
        checkIfExists(subscriptionId);
        subscriptionJpaRepository.deleteById(subscriptionId);
    }

    @Override
    public SubscriptionResponseDto getSubscription(Long subscriptionId) {
        checkIfExists(subscriptionId);
        var subscription = subscriptionJpaRepository.findById(subscriptionId).get();
        return conversionService.convert(subscription, SubscriptionResponseDto.class);
    }

    @Override
    public List<SubscriptionResponseDto> getAllSubscriptions() {
        return subscriptionJpaRepository.findAll().stream()
                .map(subscription -> conversionService.convert(subscription, SubscriptionResponseDto.class))
                .collect(Collectors.toList());
    }

    private void checkIfExists(Long subscriptionId) {
        if (subscriptionJpaRepository.findById(subscriptionId).isEmpty()) {
            throw new EntityNotFoundException(format("Subscription with ID %s already exists", subscriptionId));
        }
    }

    private void checkIfNotExists(Long subscriptionId) {
        if (subscriptionJpaRepository.findById(subscriptionId).isPresent()) {
            throw new EntityAlreadyExistsException(format("Subscription with ID %s already exists", subscriptionId));
        }
    }
}
