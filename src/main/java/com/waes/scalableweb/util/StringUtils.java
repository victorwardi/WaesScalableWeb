package com.waes.scalableweb.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 * @autor Victor Wardi - @victorwardi on 2/10/2020
 */
public class StringUtils {




    private static boolean isStringValid(String input) {
        return input != null && !input.isBlank();
    }

    public static String convertEncodedStringOnBase64toString(String encodedString) throws Exception {

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
     * Check if string is encoded on base64
     *
     * @param input
     * @return boolean
     */
    public static boolean isStringEncodedOnBase64(String input) {
        return isStringValid(input) && Base64.isBase64(input);
    }


    public static String getStringEncodedOnBase64(String encodedString) throws Exception {

        if (isStringValid(encodedString)) {
            byte[] decodedBytes = Base64.decodeBase64(encodedString);
            return new String(decodedBytes);
        }else{
            throw new Exception("String: \"" + encodedString + "\" is not encoded on base 64.");
        }

    }

    /**
     * Check if string is a valid Json
     *
     * @param input
     * @return boolean
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
