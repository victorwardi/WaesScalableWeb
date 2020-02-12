package com.waes.scalableweb.model;

import com.waes.scalableweb.enuns.DifferenceResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @autor Victor Wardi - @victorwardi on 2/12/2020
 */
class DifferenceTest {

    @Test
    void shouldCompareSidesReturnEquals() {
        Difference difference = Difference.builder()
            .leftData("abcd")
            .rightData("abcd")
            .build();

        assertThat(difference.compareSides().getDifferenceResult()).isEqualTo(DifferenceResult.EQUALS);

    }

    @Test
    void shouldCompareSidesReturnDifferentSize() {

        Difference difference = Difference.builder()
            .leftData("abcde")
            .rightData("abcd")
            .build();

        assertThat(difference.compareSides().getDifferenceResult()).isEqualTo(DifferenceResult.DIFFERENT_SIZES);
    }

    @Test
    void shouldCompareSidesReturnSameSizeDifferentContents() {

        Difference difference = Difference.builder()
            .leftData("abcd11aa2222jjbb")
            .rightData("abcd22aa3333jjba")
            .build();

        assertThat(difference.compareSides().getDifferenceResult()).isEqualTo(DifferenceResult.SAME_SIZE_DIFFERENT_CONTENTS);
    }
}