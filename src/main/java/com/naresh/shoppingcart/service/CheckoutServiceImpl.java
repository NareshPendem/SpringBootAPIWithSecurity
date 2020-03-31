package com.naresh.shoppingcart.service;

import com.naresh.shoppingcart.controller.CheckoutController;
import com.naresh.shoppingcart.exception.InvalidInputDataException;
import com.naresh.shoppingcart.helper.CheckoutValidator;
import com.naresh.shoppingcart.model.CartDataRequest;
import com.naresh.shoppingcart.model.CartDataResponse;
import com.naresh.shoppingcart.model.ProductLineItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation of CheckoutService
 */
@Service
public class CheckoutServiceImpl implements CheckoutService {
    Logger logger = LoggerFactory.getLogger(CheckoutServiceImpl.class);
    @Override
    public CartDataResponse processCartData(CartDataRequest cartDataRequest) throws InvalidInputDataException {
        logger.debug("ENTRY");
        // Validate and Assert Input Request.
        CheckoutValidator.isInputValid(cartDataRequest);
        logger.debug("Request Validation Successful");
        // Process Input Request to build mock JSON Response.
        return processRequest(cartDataRequest);
    }

    /**
     * This method calculates itemized pricing and cart pricing and tax applied.
     * @param cartDataRequest
     * @return
     */
    private CartDataResponse processRequest(CartDataRequest cartDataRequest){
        logger.debug("ENTRY");
        BigDecimal currencyRate = cartDataRequest.getCurrencyRate();
        BigDecimal taxRate = cartDataRequest.getTaxRate();
        // Calculate and set TotalPrice and TaxApplied for a given product/quantity.
        cartDataRequest.getProductLineItems().stream().forEach(
                pItem -> {
                    BigDecimal itemPrice = pItem.getProduct().getPrice().
                            multiply(new BigDecimal(pItem.getQuantity()))
                            .multiply(currencyRate);
                    BigDecimal taxPrice = itemPrice.multiply(taxRate.divide(new BigDecimal(100)));
                    pItem.setTaxPriceApplied(taxPrice);
                    pItem.setTotalPrice(itemPrice.add(taxPrice));
                }
        );

        CartDataResponse cartDataResponse = new CartDataResponse();
        List<ProductLineItem> productLineItemList = cartDataRequest.getProductLineItems();
        // Calculate and set TotalCartPrice and TotalCartTaxApplied for a given transaction.
        BigDecimal totalCartPrice = productLineItemList.stream().map(p -> p.getTotalPrice()).reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal totalCartTaxApplied = productLineItemList.stream().map(p -> p.getTaxPriceApplied()).reduce(BigDecimal.ZERO,BigDecimal::add);

        cartDataResponse.setProductLineItems(productLineItemList);
        cartDataResponse.setTotalCartPrice(totalCartPrice);
        cartDataResponse.setTotalTaxApplied(totalCartTaxApplied);
        logger.debug("processRequest Successful");
        return cartDataResponse;

    }

}
