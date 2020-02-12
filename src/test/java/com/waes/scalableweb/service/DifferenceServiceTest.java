package com.waes.scalableweb.service;

import com.waes.scalableweb.enuns.DifferenceResult;
import com.waes.scalableweb.model.Difference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 * @autor Victor Wardi - @victorwardi on 2/11/2020
 */
class DifferenceServiceTest {

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