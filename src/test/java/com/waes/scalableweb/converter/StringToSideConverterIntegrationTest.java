package com.waes.scalableweb.converter;

import com.waes.scalableweb.enuns.Side;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class for testing the conversion of any string in a Enum Side
 *
 * @autor Victor Wardi - @victorwardi on 2/10/2020
 */

@SpringBootTest
class StringToSideConverterIntegrationTest {

    @Autowired
    ConversionService conversionService;

    @Test
    public void convertValidStringsToSideEnum() {
        assertThat(conversionService.convert("LEFT", Side.class)).isEqualTo(Side.LEFT);
        assertThat(conversionService.convert("RIGHT", Side.class)).isEqualTo(Side.RIGHT);
    }



    @Test
    public void convertValidCaseInsensitiveStringsToSideEnum() {
        //Testing LEFT variations
        assertThat(conversionService.convert("Left", Side.class)).isEqualTo(Side.LEFT);
        assertThat(conversionService.convert("left", Side.class)).isEqualTo(Side.LEFT);
        assertThat(conversionService.convert("LeFt", Side.class)).isEqualTo(Side.LEFT);

        //Testing RIGHT variations
        assertThat(conversionService.convert("Right", Side.class)).isEqualTo(Side.RIGHT);
        assertThat(conversionService.convert("right", Side.class)).isEqualTo(Side.RIGHT);
        assertThat(conversionService.convert("riGht", Side.class)).isEqualTo(Side.RIGHT);
    }


    @Test()
    public void convertInvalidStringsToSideEnumShouldThrowsException() {

        Assertions.assertThrows(ConversionFailedException.class, () -> {
            conversionService.convert("TOP", Side.class);
        });

        Assertions.assertThrows(ConversionFailedException.class, () -> {
            conversionService.convert("BOTTOM", Side.class);
        });


    }


}