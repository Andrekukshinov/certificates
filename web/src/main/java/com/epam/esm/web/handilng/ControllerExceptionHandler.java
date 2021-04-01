package com.epam.esm.web.handilng;


import com.epam.esm.web.model.ExceptionModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LogManager.getLogger(ControllerExceptionHandler.class);
    private static final int INTERNAL_SERVER_ERROR = 500_01;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        return super.handleBindException(ex, headers, status, request);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        String bodyMessage = "Internal server error";
        LOGGER.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, new ExceptionModel(bodyMessage, INTERNAL_SERVER_ERROR), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

//    @ExceptionHandler(value = IllegalAccessException.class)
//    protected ResponseEntity<Object> handleConflict(IllegalAccessException ex, WebRequest request) {
//        String bodyMessage = "Internal server error";
//        LOGGER.error(ex.getMessage(), ex);
//        return handleExceptionInternal(ex, new ExceptionModel(bodyMessage, INTERNAL_SERVER_ERROR), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
//    }
}
// TODO: 31.03.2021 dto convertion, validators, date to iso(read about dateTime in mysql), logger, url mappings
// TODO: 31.03.2021 tests, javadocs, exceptions with code, expand exceptions, add id hashcode to tag hashcode

// TODO: 31.03.2021 error with enum sort(list of args), dto convertion, logger, url mappings, exceptions with code
//  OPTIONAL: date to iso(read about dateTime in mysql), validators
