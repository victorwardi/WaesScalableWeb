package com.waes.scalableweb.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.waes.scalableweb.enuns.DifferenceResult;
import com.waes.scalableweb.model.DifferenceDetail;
import lombok.Builder;
import lombok.Data;

/**
 * Represents the response of the endpoint /v1/diff/{1} GET request.
 *
 * @autor Victor Wardi - @victorwardi on 2/10/2020
 */

@Data
@Builder
public class DifferenceResponseDTO {

    private String id;

    @JsonProperty("result")
    private DifferenceResult differenceResult;

    @JsonProperty("details")
    private List<DifferenceDetail> differenceDetails;


}


