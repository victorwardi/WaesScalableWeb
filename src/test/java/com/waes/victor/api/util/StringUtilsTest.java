package com.waes.victor.api.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests of StringUtils
 *
 * @author Victor Wardi - @victorwardi on 2/10/2020
 * @see StringUtils
 */
class StringUtilsTest {

    /**
     * The constant WE_ARE_WAES.
     */
    public static final String WE_ARE_WAES = "WE ARE WAES";

    /**
     * The constant WE_ARE_WAES_ON_BASE64.
     */
    public static final String WE_ARE_WAES_ON_BASE64 = "V0UgQVJFIFdBRVM=";

    /**
     * The constant NOT_ON_BASE64.
     */
    public static final String NOT_ON_BASE64 = "!@$#%%$&()=";

    /**
     * Should return true if string is valid json.
     */
    @Test
    void shouldReturnTrueIfStringIsValidJson() {

        assertEquals(true, StringUtils.isStringValidJson("{\"name\":\"Victor\",\"age\":35,\"hired\":true}"));

        assertEquals(true, StringUtils.isStringValidJson("{ \"techs\" : [ {\"name\" : \"Java\"},{\"name\" : \"JUnit\"} ]}"));

        assertEquals(true, StringUtils.isStringValidJson("{}"));

        assertEquals(true, StringUtils.isStringValidJson("[]"));
    }

    /**
     * Should return false if string is invalid json.
     */
    @Test
    void shouldReturnFalseIfStringIsInvalidJson() {

        assertEquals(false, StringUtils.isStringValidJson("{\"name\":\"Victor\" -- ,\"age\": 35,\"hired\":true}"));

        assertEquals(false, StringUtils.isStringValidJson("{"));

        assertEquals(false, StringUtils.isStringValidJson("["));

        assertEquals(false, StringUtils.isStringValidJson("2"));

        assertEquals(false, StringUtils.isStringValidJson("null"));
    }

    /**
     * Should return false if string is invalid.
     */
    @Test
    void shouldReturnFalseIfStringIsInvalid() {

        assertEquals(false, StringUtils.isStringValid(null));

        assertEquals(false, StringUtils.isStringValid(""));

        assertEquals(false, StringUtils.isStringValid("    "));
    }

    /**
     * Should return encoded string on base 64.
     *
     * @throws Exception the exception
     */
    @Test
    void shouldReturnEncodedStringOnBase64() throws Exception {

        assertThat(StringUtils.encodeStringOnBase64(WE_ARE_WAES)).isEqualTo(WE_ARE_WAES_ON_BASE64);
    }

    /**
     * Should throw exception if string to encode is invalid.
     */
    @Test
    void shouldThrowExceptionIfStringToEncodeIsInvalid() {

        Exception exception = assertThrows(Exception.class, () -> StringUtils.encodeStringOnBase64(null));
        assertThat(exception.getMessage()).contains("String invalid! Was not possible do encode on base 64.");
    }


    /**
     * Should return decoded string from base 64.
     *
     * @throws Exception the exception
     */
    @Test
    void shouldReturnDecodedStringFromBase64() throws Exception {
        assertThat(StringUtils.decodeStringFromBase64(WE_ARE_WAES_ON_BASE64)).isEqualTo(WE_ARE_WAES);
    }

    /**
     * Should throw exception if string to decode is not on base 64.
     */
    @Test
    void shouldThrowExceptionIfStringToDecodeIsNotOnBase64() {

        Exception exception = assertThrows(Exception.class, () -> StringUtils.decodeStringFromBase64(NOT_ON_BASE64));

        assertThat(exception.getMessage()).contains("String: \"" + NOT_ON_BASE64 + "\" is not encoded on base 64.");
    }

    /**
     * Should return true if string is on base 64.
     */
    @Test
    void shouldReturnTrueIfStringIsOnBase64() {
        assertTrue(StringUtils.isStringEncodedOnBase64(WE_ARE_WAES_ON_BASE64));
    }

    /**
     * Should return false if string is not on base 64.
     */
    @Test
    void shouldReturnFalseIfStringIsNotOnBase64() {
        assertFalse(StringUtils.isStringEncodedOnBase64(NOT_ON_BASE64));
    }

}