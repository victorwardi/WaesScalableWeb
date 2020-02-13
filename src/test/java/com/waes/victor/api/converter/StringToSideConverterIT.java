package com.waes.victor.api.converter;

import com.waes.victor.api.enuns.Side;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class for testing the conversion of any string in a Enum Side
 *
 * @author Victor Wardi - @victorwardi on 2/10/2020
 */

@SpringBootTest
class StringToSideConverterIT {

    @Autowired
    ConversionService conversionService;

    @ParameterizedTest(name = "Test valid side: {arguments}")
    @ValueSource(strings = {"LEFT", "Left", "left", "RIGHT", "Right", "right"})
    void shouldConvertStringIntoSideEnum(String side) {
        assertThat(conversionService.convert(side, Side.class)).isInstanceOf(Side.class);

    }

    @ParameterizedTest(name = "Test invalid side: {arguments}")
    @ValueSource(strings = {"LETF", "TOP", "new", "rihtg", ""})
    void shouldThrowExceptionIfStringIsInvalid(String side) {
        assertThrows(ConversionFailedException.class, () -> conversionService.convert(side, Side.class));
    }

}


