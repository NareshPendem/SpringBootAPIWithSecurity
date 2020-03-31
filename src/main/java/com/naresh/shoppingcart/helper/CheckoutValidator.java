package com.naresh.shoppingcart.helper;

import com.naresh.shoppingcart.exception.InvalidInputDataException;
import com.naresh.shoppingcart.exception.handler.GlobalExceptionHandler;
import com.naresh.shoppingcart.model.CartDataRequest;
import com.naresh.shoppingcart.model.CartDataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CheckoutValidator {

    static Logger logger = LoggerFactory.getLogger(CheckoutValidator.class);
    public static boolean isInputValid(CartDataRequest cartDataRequest) throws InvalidInputDataException {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CartDataRequest>> violations = validator.validate(cartDataRequest);
        List<String> errorMsg = new ArrayList<>();
        for (ConstraintViolation<CartDataRequest> violation : violations) {
            errorMsg.add(violation.getMessage());
        }
        if(!errorMsg.isEmpty()) {
            CartDataResponse cartDataResponse = new CartDataResponse();
            cartDataResponse.setErrors(errorMsg);
            InvalidInputDataException exception =  new InvalidInputDataException();
            exception.setErrorResponse(cartDataResponse);
            throw exception;
        }
        logger.debug("Validation Success");
        return true;
    }
}
