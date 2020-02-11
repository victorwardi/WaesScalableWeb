package com.waes.scalableweb.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import com.waes.scalableweb.validation.EncodedOnBase64;
import lombok.Data;

/**
 * Represents the content of the endpoint /v1/{id}/{side} POST request.
 *
 * @autor Victor Wardi - @victorwardi on 2/10/2020
 */
@Data
public class DifferenceRequestDTO implements Serializable {


    @NotNull
    @NotEmpty
    @EncodedOnBase64
    private String encodedData;
}
