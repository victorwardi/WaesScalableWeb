package com.waes.scalableweb.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * The type Scalable web exception handler.
 *
 * @author Victor Wardi
 */
@ControllerAdvice
public class ScalableWebExceptionHandler extends ResponseEntityExceptionHandler {
      // 404



    // 400



//    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
//    public ResponseEntity<Object> handleBadRequest(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
//        final String bodyOfResponse = "This should be application specific A";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler({ DataIntegrityViolationException.class })
//    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
//        final String bodyOfResponse = "This should be application specific B";
//
//
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
//
//        List<String> details = new ArrayList<>();
//        details.add(ex.getMessage());
//        details.add(ex.getLocalizedMessage());
//        details.add("Deu merda!");
//        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now().toString(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), "Deu Ruim", request.getContextPath(), details);
//        return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
//        final String bodyOfResponse = "This should be application specific D";
//        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
//    }



}

