package com.waes.victor.api.controller;

import javax.validation.Valid;

import com.waes.victor.api.dto.DifferenceRequestDTO;
import com.waes.victor.api.dto.DifferenceResponseDTO;
import com.waes.victor.api.enuns.Side;
import com.waes.victor.api.service.DifferenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller responsible for provide DIFF endpoints
 *
 * @author Victor Wardi - @victorwardi on 2/8/2020
 */
@RestController
@RequestMapping("v1/diff")
@Api(value = "Rest Controller responsible for provide DIFF endpoints", tags="Difference Controller" )
public class DifferenceController {

    private final DifferenceService differenceService;

    public DifferenceController(DifferenceService differenceService) {
        this.differenceService = differenceService;
    }

    @ApiOperation(value = "Endpoint to add data to left/right side.")
    @PostMapping(value = "/{id}/{side}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addEncodedDataToSideWithID(@ApiParam(value="Operation ID") @PathVariable final String id,
                                           @ApiParam(value="Side of the operation", allowableValues = "left, right") @PathVariable final Side side,
                                           @Valid @RequestBody DifferenceRequestDTO differenceRequest) {

            this.differenceService.addDifferenceSideData(id, side, differenceRequest);
    }

    @ApiOperation(value = "Endpoint to get the differences from two strings according to operation id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public DifferenceResponseDTO getDifferenceById(@ApiParam(value="Operation ID") @PathVariable final String id) {
        return this.differenceService.getDifference(id);

    }

}