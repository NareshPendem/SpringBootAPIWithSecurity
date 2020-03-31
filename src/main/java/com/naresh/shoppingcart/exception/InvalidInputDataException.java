package com.naresh.shoppingcart.exception;

import com.naresh.shoppingcart.model.CartDataResponse;

public class InvalidInputDataException extends RuntimeException{

    private static final long serialVersionUID = -5310416991635989692L;
    private CartDataResponse errorResponse;

    public CartDataResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(CartDataResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
