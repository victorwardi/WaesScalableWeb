package com.waes.scalableweb.controller.util;


import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Victor Wardi - @victorwardi on 2/8/2020
 */
public class JsonUtil {


    /**
     * @return A Json String enconded on Base64
     */
    public static String convertStringOnBase64(String originalJsonString) {

        originalJsonString = "{ 'name': 'Victor Wardi', 'role':'Java Developer' }";

        String encodedJsonString = Base64.encodeBase64String(originalJsonString.getBytes());

        return encodedJsonString;
    }

    /**
     * Check if string is a valid Json
     *
     * @param input
     * @return boolean
     */
    public static boolean isStringAValidJson(String input) {

        if (input != null && !input.isBlank()) {
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

    public static String convertEncodedStringOnBase64toString(String encodedString) throws Exception {

        String decodedString = "";

        if (encodedString != null && !encodedString.isBlank()) {
            if (Base64.isBase64(encodedString)) {
                byte[] decodedBytes = Base64.decodeBase64(encodedString);
                decodedString = new String(decodedBytes);
            } else {
                throw new Exception("String: \"" + encodedString + "\" is not encoded on base 64.");
            }
        }

        return decodedString;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(convertEncodedStringOnBase64toString(convertStringOnBase64("")));
    }

    /**
     * Check if string is encoded on base64
     *
     * @param input
     * @return boolean
     */
    public static boolean isStringEncodedOnBase64(String input) {

        if (input != null && !input.isBlank()) {
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
