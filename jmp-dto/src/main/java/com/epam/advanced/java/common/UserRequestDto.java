package com.epam.advanced.java.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel("User Request")
@Data
@Builder
public class UserRequestDto {

    @ApiModelProperty("ID of User record")
    private Long id;

    @ApiModelProperty("User's name")
    private String name;

    @ApiModelProperty("User's Last Name")
    private String surname;

    @ApiModelProperty("User's birthday date")
    private String birthday;
}
