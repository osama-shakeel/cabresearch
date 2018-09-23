package com.au.research.cab.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class APIExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(APIExceptionHandler.class);

    @ExceptionHandler({ ApplicationException.class })
    public ResponseEntity<ApiError> handleApplicationException(ApplicationException ex, WebRequest request) {
        logger.error("Handling {}: {}", ex.getClass().getName(), ex.getMessage());
        ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Application Error");
        return new ResponseEntity<ApiError>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        logger.error("Invalid API parameters used", ex);
        ApiError apiError = new ApiError(
                ex.getName() + " parameter of type " + ex.getRequiredType().getName() + " expected",
                HttpStatus.BAD_REQUEST.value(), "Bad Request");
        return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
    }
}
