package com.naresh.shoppingcart.service;

import com.naresh.shoppingcart.exception.InvalidInputDataException;
import com.naresh.shoppingcart.model.CartDataRequest;
import com.naresh.shoppingcart.model.CartDataResponse;

/**
 * Service class which creates a mock Cart Response with pricing
 * information at Cart and Items level.
 */
public interface CheckoutService {
    CartDataResponse processCartData(CartDataRequest cartDataRequest) throws InvalidInputDataException;
}
