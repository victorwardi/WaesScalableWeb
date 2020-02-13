package com.waes.victor.api.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 * Utility methods to validate strings
 *
 * @author Victor Wardi - @victorwardi on 2/10/2020
 */
public class StringUtils {


    /**
     * Is string valid .
     *
     * @param input the input
     * @return true or false
     */
    public static boolean isStringValid(String input) {
        return input != null && !input.isBlank();
    }

    /**
     * Convert encoded string on base 64 to string string.
     *
     * @param encodedString the encoded string
     * @return the string
     * @throws Exception the exception
     */
    public static String decodeStringFromBase64(String encodedString) throws Exception {

        String decodedString = "";

        if (isStringValid(encodedString)) {
            if (Base64.isBase64(encodedString)) {
                byte[] decodedBytes = Base64.decodeBase64(encodedString);
                decodedString = new String(decodedBytes);
            } else {
                throw new Exception("String: \"" + encodedString + "\" is not encoded on base 64.");
            }
        }

        return decodedString;
    }


    /**
     * Encode string on base 64 string.
     *
     * @param string the string
     * @return the string
     * @throws Exception the exception
     */
    public static String encodeStringOnBase64(String string) throws Exception {

        if (isStringValid(string)) {
            return Base64.encodeBase64String(string.getBytes());
        } else {
            throw new Exception("String invalid! Was not possible do encode on base 64.");
        }
    }

    /**
     * Check if string is encoded on base64
     *
     * @param input the input
     * @return boolean boolean
     */
    public static boolean isStringEncodedOnBase64(String input) {
        return isStringValid(input) && Base64.isBase64(input);
    }


    /**
     * Check if string is a valid Json
     *
     * @param input the input
     * @return boolean boolean
     */
    public static boolean isStringValidJson(String input) {

        if (isStringValid(input)) {
            try {
                new JSONObject(input);
            } catch (JSONException objectEx) {
                try {
                    new JSONArray(input);
                } catch (JSONException arrayEx) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
