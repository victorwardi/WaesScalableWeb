package com.waes.victor.api.exception;


import java.util.ArrayList;
import java.util.List;

import com.waes.victor.api.contants.WaesApiMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Waes API exception handler.
 *
 * @author Victor Wardi
 */
@ControllerAdvice
public class WaesApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle waes api runtime exception response entity.
     *
     * @param ex      the exception
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(WaesApiRuntimeException.class)
    public ResponseEntity<Object> handleWaesApiRuntimeException(WaesApiRuntimeException ex, WebRequest request) {

        WaesApiErrorResponse apiErrorResponse =  WaesApiErrorResponse.builder()
            .status(ex.getStatus())
            .message(ex.getMessage())
            .build();

        return super.handleExceptionInternal(ex, apiErrorResponse, null, apiErrorResponse.getStatus(), request);
    }

    /**
     * Handle method argument type mismatch exception response entity.
     *
     * @param ex      the exception
     * @param headers the headers
     * @param status  the status
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleExceptionInternal(ex, null, headers, status, request);
    }

    /**
     * Override the handle method argument type mismatch exception response entity.
     * @param ex      the exception
     * @param headers the headers
     * @param status  the status
     * @param request the request
     * @return the response entity
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        WaesApiErrorResponse apiErrorResponse =  WaesApiErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message(WaesApiMessage.ARGUMENT_NOT_VALID)
            .trace(ex.getMessage())
            .build();

        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        apiErrorResponse.setErrors(errors);

        return handleExceptionInternal(ex, apiErrorResponse, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        WaesApiErrorResponse apiErrorResponse =  WaesApiErrorResponse.builder()
            .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
            .message(WaesApiMessage.CONTENT_TYPE_NOT_VALID)
            .trace(ex.getMessage())
            .build();

        return handleExceptionInternal(ex, apiErrorResponse, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        WaesApiErrorResponse apiErrorResponse =  WaesApiErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message(WaesApiMessage.REQUEST_BODY_IS_MISSING)
            .trace(ex.getMessage())
            .build();

        return handleExceptionInternal(ex, apiErrorResponse, headers, status, request);
    }
}

