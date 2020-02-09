package com.waes.scalableweb.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.waes.scalableweb.validation.EncodedOnBase64;
import lombok.Data;

/**
 * @autor Victor Wardi - @victorwardi on 2/9/2020
 */


@Data
public class DifferenceRequestDTO {

    @JsonProperty(value = "String encoded on base 64", required = true)
    @NotNull(message = "A string must be provided.")
    @NotEmpty(message = "A string should not be empty.")
    @EncodedOnBase64
    String encondedData;
}
