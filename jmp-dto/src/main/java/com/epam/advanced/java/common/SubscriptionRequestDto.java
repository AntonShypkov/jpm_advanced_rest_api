package com.epam.advanced.java.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel("Subscription Request")
@Data
@Builder
public class SubscriptionRequestDto {

    @ApiModelProperty("ID of Subscription record")
    private Long id;

    @ApiModelProperty("ID of User who has the Subscription")
    private Long userId;

    @ApiModelProperty("Date from which the subscription is enabled")
    private String startDate;

}
