package com.waes.scalableweb.exception;


import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * The type Scalable web exception handler.
 *
 * @author Victor Wardi
 */
@ControllerAdvice
public class WaesApiExceptionHandler extends ResponseEntityExceptionHandler {



//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,  HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        List<String> errors = new ArrayList<>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.add(error.getField() + ": " + error.getDefaultMessage());
//        }
//        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//        }
//
//        ApiErrorResponse apiError = ApiErrorResponse.builder()
//            .timestamp(LocalDateTime.now())
//            .status(HttpStatus.BAD_REQUEST)
//            .message(ex.getLocalizedMessage())
//            .errors(errors)
//            .path(getRequestPath(request))
//            .build();
//
//        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
//    }
//
//
//    @ExceptionHandler({ ConstraintViolationException.class })
//    public ResponseEntity<Object> handleConstraintViolation( ConstraintViolationException ex, WebRequest request) {
//                ApiErrorResponse errorResponse = getApiErrorResponse(HttpStatus.BAD_REQUEST, ex, request);
//
//        List<String> errors = new ArrayList<>();
//
//        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//            errors.add(violation.getRootBeanClass().getName() + " " +
//                violation.getPropertyPath() + ": " + violation.getMessage());
//        }
//
//
//        errorResponse.setErrors(errors);
//
//        return new ResponseEntity<>(errorResponse, new HttpHeaders(), errorResponse.getStatus());
//    }


    @ExceptionHandler({ WaesApiRuntimeException.class })
    public ResponseEntity<Object> handleWaesApiRuntimeException(WaesApiRuntimeException ex, WebRequest request) {


        ApiErrorResponse apiError = getApiErrorResponse(ex.getStatus(), ex, request);

        return new ResponseEntity<>(apiError, new HttpHeaders(), ex.getStatus());
    }

    private ApiErrorResponse getApiErrorResponse(HttpStatus status, Exception ex, WebRequest request) {
        return ApiErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(status)
            .message(ex.getLocalizedMessage())
            .path(getRequestPath(request))
            .build();
    }


    private String getRequestPath(WebRequest request) {
        if (request == null) {
            return "";
        } else {
            return ((ServletWebRequest) request).getRequest().getRequestURI();
        }
    }

}

