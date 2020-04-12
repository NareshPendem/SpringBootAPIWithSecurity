package com.naresh.shoppingcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(
            value="admin",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String welcomeMsg(){
        return "Welcome Admin";
    }
}
