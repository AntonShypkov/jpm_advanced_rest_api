package com.epam.advanced.java.controller.v2;

import com.epam.advanced.java.common.SubscriptionRequestDto;
import com.epam.advanced.java.common.SubscriptionResponseDto;
import com.epam.advanced.java.service.SubscriptionV2Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@Api(tags = {"V2 Subscription Service (database storage)"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/study/v2/subscription")
@Slf4j
public class ServiceV2Controller {

    private final SubscriptionV2Service subscriptionV2Service;

    @ApiOperation("Creates new subscription")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Created new subscription")
    })
    @PostMapping
    public SubscriptionResponseDto createSubscription(
            @ApiParam("Request with subsciption data")
            @RequestBody SubscriptionRequestDto subscriptionRequest) {
        log.info("V2: Create new subscription with data: {}", subscriptionRequest);
        return subscriptionV2Service.createSubscription(subscriptionRequest);
    }

    @ApiOperation("Updates existent subscription")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Updated existent subscription")
    })
    @PutMapping("/{subscriptionId}")
    public SubscriptionResponseDto updateSubscription(@ApiParam("Subscription ID to be updated")
            @PathVariable Long subscriptionId,
            @ApiParam("Subscription update data")
            @RequestBody SubscriptionRequestDto subscriptionRequest) {
        log.info("V2: Update subscription with id {}", subscriptionId);
        var response = subscriptionV2Service.updateSubscription(subscriptionId, subscriptionRequest);

        log.info("V2: Response {}", response);
        return response;
    }

    @ApiOperation("Removes existent subscription by ID")
    @DeleteMapping("/{subscriptionId}")
    public void deleteSubscription(
            @ApiParam("Subscription ID to be removed")
            @PathVariable Long subscriptionId) {
        log.info("V2: Delete subscription with id {}", subscriptionId);
        subscriptionV2Service.deleteSubscription(subscriptionId);
    }

    @ApiOperation("Extracts subscriptions with provided ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returned subscriptions with provided ID"),
            @ApiResponse(code = 404, message = "Subscription with provided ID not found")
    })
    @GetMapping("/{subscriptionId}")
    public SubscriptionResponseDto getSubscription(
            @ApiParam("Subscription ID to be selected")
            @PathVariable Long subscriptionId) {
        log.info("V2: Get subscription with id {}", subscriptionId);
        var response = subscriptionV2Service.getSubscription(subscriptionId);

        log.info("V2: Response {}", response);
        return response;
    }

    @ApiOperation("Extracts all existent subscriptions")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returned all existent subscriptions")
    })
    @GetMapping("/all")
    public List<SubscriptionResponseDto> getAllSubscription() {
        log.info("V2: Get all existent subscriptions");
        var response = subscriptionV2Service.getAllSubscriptions();

        log.info("V2: Response {}", response);
        return response;
    }
}
