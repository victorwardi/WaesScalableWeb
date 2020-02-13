package com.waes.victor.api.model;

import com.waes.victor.api.contants.WaesApiMessage;
import com.waes.victor.api.enuns.DifferenceResult;
import com.waes.victor.api.exception.WaesApiRuntimeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Victor Wardi - @victorwardi on 2/12/2020
 */
class DifferenceTest {

    @Test
    void shouldCompareSidesReturnEquals() {
        Difference difference = Difference.builder()
            .leftData("abcd")
            .rightData("abcd")
            .build();

        Assertions.assertThat(difference.compareSides().getDifferenceResult()).isEqualTo(DifferenceResult.EQUALS);

    }

    @Test
    void shouldCompareSidesReturnDifferentSize() {

        Difference difference = Difference.builder()
            .leftData("abcde")
            .rightData("abcd")
            .build();

        Assertions.assertThat(difference.compareSides().getDifferenceResult()).isEqualTo(DifferenceResult.DIFFERENT_SIZES);
    }

    @Test
    void shouldCompareSidesReturnSameSizeDifferentContents() {

        Difference difference = Difference.builder()
            .leftData("abcd11aa2222jjbb")
            .rightData("abcd22aa3333jjba")
            .build();

        Assertions.assertThat(difference.compareSides().getDifferenceResult()).isEqualTo(DifferenceResult.SAME_SIZE_DIFFERENT_CONTENTS);
    }


    @Test
    void shouldCompareSidesThrowExceptionIfLeftSideIsEmpty() {

        Difference difference = Difference.builder()
            .leftData("")
            .rightData("abcd")
            .build();

        Exception exception = assertThrows(WaesApiRuntimeException.class, difference::compareSides);

        org.junit.jupiter.api.Assertions.assertEquals(WaesApiMessage.LEFT_SIDE_NOT_FOUND, exception.getMessage());
    }


    @Test
    void shouldCompareSidesThrowExceptionIfRightSideIsEmpty() {

        Difference difference = Difference.builder()
            .leftData("abcd")
            .rightData("")
            .build();

        Exception exception = assertThrows(WaesApiRuntimeException.class, difference::compareSides);

        assertEquals(WaesApiMessage.RIGHT_SIDE_NOT_FOUND, exception.getMessage());
    }


    @Test
    void shouldCompareSidesThrowExceptionIfSidesAreEmpty() {

        Difference difference = Difference.builder()
            .leftData("")
            .rightData("")
            .build();

        Exception exception = assertThrows(WaesApiRuntimeException.class, difference::compareSides);

        assertEquals(WaesApiMessage.SIDES_NOT_FOUND, exception.getMessage());
    }
}