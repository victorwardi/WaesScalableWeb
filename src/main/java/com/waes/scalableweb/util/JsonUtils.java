package com.waes.scalableweb.util;


import org.apache.commons.codec.binary.Base64;

/**
 * Created by Victor Wardi - @victorwardi on 2/8/2020
 */
public class JsonUtils {


    /**
     * @return A Json String enconded on Base64
     */
    public static String convertStringOnBase64(String originalJsonString) {

        originalJsonString = "{ 'name': 'Victor Wardi', 'role':'Java Developer' }";

        String encodedJsonString = Base64.encodeBase64String(originalJsonString.getBytes());

        return encodedJsonString;
    }







}
