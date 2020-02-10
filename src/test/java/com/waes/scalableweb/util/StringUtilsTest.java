package com.waes.scalableweb.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @autor Victor Wardi - @victorwardi on 2/10/2020
 */
class StringUtilsTest {

    @Test
    void shouldReturnTrueIfStringIsValidJson() {

        assertEquals(true, StringUtils.isStringValidJson("{\"name\":\"Victor\",\"age\":35,\"hired\":true}"));

        assertEquals(true, StringUtils.isStringValidJson("{ \"techs\" : [ {\"name\" : \"Java\"},{\"name\" : \"JUnit\"} ]}"));

        assertEquals(true, StringUtils.isStringValidJson("{}"));

        assertEquals(true, StringUtils.isStringValidJson("[]"));
    }

    @Test
    void shouldReturnFalseIfStringIsEmpty() {
        assertEquals(false, StringUtils.isStringValidJson(""));
    }

    @Test
    void shouldReturnFalseIfStringIsInvalidJson() {
        assertEquals(false, StringUtils.isStringValidJson("{\"nome\" :, \"victor\"}"));
    }
}