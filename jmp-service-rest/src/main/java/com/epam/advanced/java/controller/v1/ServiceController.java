package com.epam.advanced.java.controller.v1;

import com.epam.advanced.java.common.SubscriptionRequestDto;
import com.epam.advanced.java.common.SubscriptionResponseDto;
import com.epam.advanced.java.service.SubscriptionService;
import io.swagger.annotations.Api;
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

@Api(tags = {"V1 Subscription Service (runtime in-memory storage)"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/study/v1/subscription")
@Slf4j
public class ServiceController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public SubscriptionResponseDto createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequest) {
        log.info("V1: Create new subscription with data: {}", subscriptionRequest);
        return subscriptionService.createSubscription(subscriptionRequest);
    }

    @PutMapping("/{subscriptionId}")
    public SubscriptionResponseDto updateSubscription(@PathVariable Long subscriptionId,
                                                      @RequestBody SubscriptionRequestDto subscriptionRequest) {
        log.info("V1: Update subscription with id {}", subscriptionId);
        return subscriptionService.updateSubscription(subscriptionId, subscriptionRequest);
    }

    @DeleteMapping("/{subscriptionId}")
    public void deleteSubscription(@PathVariable Long subscriptionId) {
        log.info("V1: Delete subscription with id {}", subscriptionId);
        subscriptionService.deleteSubscription(subscriptionId);
    }

    @GetMapping("/{subscriptionId}")
    public SubscriptionResponseDto getSubscription(@PathVariable Long subscriptionId) {
        log.info("V1: Get subscription with id {}", subscriptionId);
        return subscriptionService.getSubscription(subscriptionId);
    }

    @GetMapping("/all")
    public List<SubscriptionResponseDto> getAllSubscription() {
        log.info("V1: Get all existent subscriptions");
        return subscriptionService.getAllSubscriptions();
    }
}
