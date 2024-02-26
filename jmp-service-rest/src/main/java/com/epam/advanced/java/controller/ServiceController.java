package com.epam.advanced.java.controller;

import com.epam.advanced.java.common.SubscriptionRequestDto;
import com.epam.advanced.java.common.SubscriptionResponseDto;
import com.epam.advanced.java.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/study/v1/subscription")
@Slf4j
public class ServiceController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public SubscriptionResponseDto createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequest) {
        return subscriptionService.createSubscription(subscriptionRequest);
    }

    @PutMapping("/{subscriptionId}")
    public SubscriptionResponseDto updateSubscription(@PathVariable Long subscriptionId, @RequestBody SubscriptionRequestDto subscriptionRequest) {
        return subscriptionService.updateSubscription(subscriptionId, subscriptionRequest);
    }

    @DeleteMapping("/{subscriptionId}")
    public void deleteSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
    }

    @GetMapping("/{subscriptionId}")
    public SubscriptionResponseDto getSubscription(@PathVariable Long subscriptionId) {
        return subscriptionService.getSubscription(subscriptionId);
    }

    @GetMapping("/all")
    public List<SubscriptionResponseDto> getAllSubscription() {
        return subscriptionService.getAllSubscriptions();
    }
}
