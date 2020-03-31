package com.naresh.shoppingcart.controller;

import com.naresh.shoppingcart.model.CartDataRequest;
import com.naresh.shoppingcart.model.CartDataResponse;
import com.naresh.shoppingcart.service.CheckoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Checkout Controller to handle
 */
@RestController
public class CheckoutController {
    Logger logger = LoggerFactory.getLogger(CheckoutController.class);
    @Autowired
    private CheckoutService checkoutService;
    @RequestMapping(value = "/checkout",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CartDataResponse createCardData(@RequestBody CartDataRequest cartDataRequest, HttpServletRequest request){
        logger.info("Incoming Request",cartDataRequest);
        CartDataResponse cartDataResponse = checkoutService.processCartData(cartDataRequest);
        logger.info("Outgoing Response",cartDataResponse);
        return cartDataResponse;
    }
}
