package com.waes.scalableweb.exception;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @autor Victor Wardi - @victorwardi on 2/9/2020
 */
@Data
@AllArgsConstructor
public class ErrorResponse {

    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<String> details;
}

