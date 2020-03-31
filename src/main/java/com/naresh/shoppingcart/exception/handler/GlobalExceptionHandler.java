package com.naresh.shoppingcart.exception.handler;

import com.naresh.shoppingcart.exception.InvalidInputDataException;
import com.naresh.shoppingcart.model.CartDataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidInputDataException.class})
    public CartDataResponse invalidInputData(HttpServletRequest request , InvalidInputDataException e){
        logger.error("Invalid Input Data {}",e);
        return e.getErrorResponse();
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public CartDataResponse handleUnsupportMediaType(HttpMediaTypeNotSupportedException e) {
        logger.error("Media-Type Not Supported", e);
        CartDataResponse errorResponse = new CartDataResponse();
        //TODO : Pick the message from Property file.
        errorResponse.setErrors(Collections.singletonList("UnSupported Media Type"));
        return errorResponse;
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CartDataResponse handleUnsupportMediaType(NoHandlerFoundException e) {
        logger.error("Resource not available Error", e);
        CartDataResponse errorResponse = new CartDataResponse();
        //TODO : Pick the message from Property file.
        errorResponse.setErrors(Collections.singletonList("Resource Not Available"));
        return errorResponse;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CartDataResponse handleException(Exception e) {
        logger.error("Exception Occurred : ", e);
        CartDataResponse errorResponse = new CartDataResponse();
        errorResponse.setErrors(Collections.singletonList("Internal Application Error"));
        return errorResponse;
    }
}
