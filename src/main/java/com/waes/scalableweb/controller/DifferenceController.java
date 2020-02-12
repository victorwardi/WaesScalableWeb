package com.waes.scalableweb.controller;

import javax.validation.Valid;

import com.waes.scalableweb.dto.DifferenceRequestDTO;
import com.waes.scalableweb.dto.DifferenceResponseDTO;
import com.waes.scalableweb.enuns.Side;
import com.waes.scalableweb.service.DifferenceService;
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
 *
 * Rest Controller responsible for provide DIFF endpoints
 *
 * @author Victor Wardi - @victorwardi on 2/8/2020
 */
@RestController
@RequestMapping("v1/diff")
public class DifferenceController {

    private final DifferenceService differenceService;

    public DifferenceController(DifferenceService differenceService) {
        this.differenceService = differenceService;
    }

    @PostMapping(value = "/{id}/{side}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addEncodedDataToSideWithID(@PathVariable final String id, @PathVariable final Side side, @Valid @RequestBody DifferenceRequestDTO differenceRequest) {
            this.differenceService.addDifferenceSideData(id, side, differenceRequest);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DifferenceResponseDTO getDifferenceById(@PathVariable final String id) {
        return this.differenceService.getDifference(id);

    }

}