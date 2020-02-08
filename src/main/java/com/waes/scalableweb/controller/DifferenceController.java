package com.waes.scalableweb.controller;

import com.waes.scalableweb.service.DifferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Rest Controller responsable for
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

    @PostMapping(value = "/{id}/left")
    @ResponseStatus(HttpStatus.CREATED)
    public void addLeftData(@PathVariable final String id, @RequestBody final String data) {
    }

    @PostMapping(value = "/{id}/right")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRightData(@PathVariable final String id, @RequestBody final String data) {
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void getDifferenceById(@PathVariable final String id) {

    }

}