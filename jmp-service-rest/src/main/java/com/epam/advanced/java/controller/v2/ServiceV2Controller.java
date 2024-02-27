package com.epam.advanced.java.controller.v2;

import com.epam.advanced.java.common.SubscriptionRequestDto;
import com.epam.advanced.java.common.SubscriptionResponseDto;
import com.epam.advanced.java.service.SubscriptionV2Service;
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
@RequestMapping("/study/v2/subscription")
@Slf4j
public class ServiceV2Controller {

    private final SubscriptionV2Service subscriptionV2Service;

    @PostMapping
    public SubscriptionResponseDto createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequest) {
        log.info("V2: Create new subscription with data: {}", subscriptionRequest);
        return subscriptionV2Service.createSubscription(subscriptionRequest);
    }

    @PutMapping("/{subscriptionId}")
    public SubscriptionResponseDto updateSubscription(@PathVariable Long subscriptionId,
                                                      @RequestBody SubscriptionRequestDto subscriptionRequest) {
        log.info("V2: Update subscription with id {}", subscriptionId);
        var response = subscriptionV2Service.updateSubscription(subscriptionId, subscriptionRequest);

        log.info("V2: Response {}", response);
        return response;
    }

    @DeleteMapping("/{subscriptionId}")
    public void deleteSubscription(@PathVariable Long subscriptionId) {
        log.info("V2: Delete subscription with id {}", subscriptionId);
        subscriptionV2Service.deleteSubscription(subscriptionId);
    }

    @GetMapping("/{subscriptionId}")
    public SubscriptionResponseDto getSubscription(@PathVariable Long subscriptionId) {
        log.info("V2: Get subscription with id {}", subscriptionId);
        var response = subscriptionV2Service.getSubscription(subscriptionId);

        log.info("V2: Response {}", response);
        return response;
    }

    @GetMapping("/all")
    public List<SubscriptionResponseDto> getAllSubscription() {
        log.info("V2: Get all existent subscriptions");
        var response = subscriptionV2Service.getAllSubscriptions();

        log.info("V2: Response {}", response);
        return response;
    }
}
