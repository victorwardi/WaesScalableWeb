package com.waes.scalableweb.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents the content of the endpoint /v1/{id}/{side} POST request.
 *
 * @autor Victor Wardi - @victorwardi on 2/10/2020
 */
@Data
public class DifferenceRequestDTO {

    @JsonProperty(value = "ID for the comparison request", required = true)
    @NotNull(message = "A id must be provided.")
    @NotEmpty(message = "A ID should not be empty.")
    private String id;


    @JsonProperty(value = "String encoded on base 64", required = true)
    @NotNull(message = "A encoded string must be provided.")
    @NotEmpty(message = "A encoded string should not be empty.")
   // @EncodedOnBase64
    private String encodedData;
}
