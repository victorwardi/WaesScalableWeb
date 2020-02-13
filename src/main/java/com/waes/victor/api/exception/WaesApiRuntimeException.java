package com.waes.victor.api.exception;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;

/**
 * @author Victor Wardi - @victorwardi on 2/12/2020
 */

/**
 * Responsible to throw business rules exceptions
 */
public class WaesApiRuntimeException extends RuntimeException {

   private HttpStatus status;

    public WaesApiRuntimeException(String message) {
        super(message);
    }

    public WaesApiRuntimeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public WaesApiRuntimeException(String message, HttpStatus status, Object... args) {
        super(MessageFormat.format(message, args));
        this.status = status;
    }

    public WaesApiRuntimeException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public WaesApiRuntimeException(Throwable cause, HttpStatus status) {
        super(cause);
        this.status = status;
    }


    public HttpStatus getStatus() {
        return this.status;
    }

}

