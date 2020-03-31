package com.naresh.shoppingcart.controller;

import com.naresh.shoppingcart.model.CartDataRequest;
import com.naresh.shoppingcart.model.CartDataResponse;
import com.naresh.shoppingcart.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;
    @RequestMapping(value = "/checkout",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CartDataResponse createCardData(@RequestBody CartDataRequest cartDataRequest, HttpServletRequest request){
        return checkoutService.processCartData(cartDataRequest);
    }

}
