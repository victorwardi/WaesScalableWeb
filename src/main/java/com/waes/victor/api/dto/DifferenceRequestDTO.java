package com.waes.victor.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents the content of the endpoint /v1/{id}/{side} POST request.
 *
 * @author Victor Wardi - @victorwardi on 2/10/2020
 */
@Data
@ApiModel(description="Represents the content of the endpoint /v1/{id}/{side} POST request.")
public class DifferenceRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @NotEmpty
    @ApiModelProperty(value="Data encoded on Base64.", required=true)
    private String encodedData;
}
