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

/**
 * Helper class to validate Incoming Request.
 */
public class CheckoutValidator {

    static Logger logger = LoggerFactory.getLogger(CheckoutValidator.class);

    /**
     * Method is used to assert incoming request for valid data.
     * In case of invalid request, populates response with List of error messages.
     * @param cartDataRequest
     * @return boolean true - if the request is valid.
     * @throws InvalidInputDataException
     */
    public static boolean isInputValid(CartDataRequest cartDataRequest) throws InvalidInputDataException {
        logger.debug("ENTRY");
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
