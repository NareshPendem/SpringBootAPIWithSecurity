package com.naresh.shoppingcart.service;

import com.naresh.shoppingcart.exception.InvalidInputDataException;
import com.naresh.shoppingcart.model.CartDataRequest;
import com.naresh.shoppingcart.model.CartDataResponse;

public interface CheckoutService {
    CartDataResponse processCartData(CartDataRequest cartDataRequest) throws InvalidInputDataException;
}
