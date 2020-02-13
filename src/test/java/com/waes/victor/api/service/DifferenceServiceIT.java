package com.waes.victor.api.service;

import com.waes.victor.api.enuns.DifferenceResult;
import com.waes.victor.api.model.Difference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 * @author Victor Wardi - @victorwardi on 2/11/2020
 */
class DifferenceServiceIT {

    @Mock
    DifferenceService differenceService;

    Difference difference;

    @BeforeEach
    void setUp() {
        this.difference = Difference.builder().id("1")
            .rightData("ABCDEF")
            .leftData("ABCDEF").build();
    }

    @Test
    void shouldGetDifferenceThrowExceptionIfLeftSideWasNotFound() {

        //differenceService.getDifference(any())

    }

    @Test
    void addDifferenceSideData() {
    }


    private Difference getValidEqualDifference(){

        return Difference.builder().id("1")
            .rightData("ABCDEF")
            .leftData("ABCDEF")
            .differenceResult(DifferenceResult.EQUALS)
            .build();
    }

    private Difference getValidDifferentSizesDifference(){

        return Difference.builder().id("1")
            .rightData("ABCDEF")
            .leftData("ABCDEFGHIJ")
            .differenceResult(DifferenceResult.DIFFERENT_SIZES)
            .build();
    }

    private Difference getValidSameSizeDifferentContentDifference(){

       Difference difference = Difference.builder().id("1")
           .rightData("ABCDEF")
           .leftData("ABCDEFGHIJ")
           .build();

       return  difference.compareSides();

    }

}