package com.waes.victor.api.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.waes.victor.api.enuns.DifferenceResult;
import com.waes.victor.api.model.DifferenceDetail;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * Represents the response of the endpoint /v1/diff/{1} GET request.
 *
 * @author Victor Wardi - @victorwardi on 2/10/2020
 */
@Data
@Builder
@ApiModel(description="Represents the response of the endpoint /v1/{id}.")
public class DifferenceResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value ="result")
    private DifferenceResult differenceResult;

    @JsonProperty(value ="details")
    private List<DifferenceDetail> differenceDetails;

}


