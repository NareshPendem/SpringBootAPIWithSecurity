package com.naresh.shoppingcart.service;

import com.naresh.shoppingcart.exception.InvalidInputDataException;
import com.naresh.shoppingcart.helper.CheckoutValidator;
import com.naresh.shoppingcart.model.CartDataRequest;
import com.naresh.shoppingcart.model.CartDataResponse;
import com.naresh.shoppingcart.model.ProductLineItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Override
    public CartDataResponse processCartData(CartDataRequest cartDataRequest) throws InvalidInputDataException {
        CheckoutValidator.isInputValid(cartDataRequest);
        return processRequest(cartDataRequest);
    }

    private CartDataResponse processRequest(CartDataRequest cartDataRequest){
        BigDecimal currencyRate = cartDataRequest.getCurrencyRate();
        BigDecimal taxRate = cartDataRequest.getTaxRate();
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
        BigDecimal totalCartPrice = productLineItemList.stream().map(p -> p.getTotalPrice()).reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal totalCartTaxApplied = productLineItemList.stream().map(p -> p.getTaxPriceApplied()).reduce(BigDecimal.ZERO,BigDecimal::add);

        cartDataResponse.setProductLineItems(productLineItemList);
        cartDataResponse.setTotalCartPrice(totalCartPrice);
        cartDataResponse.setTotalTaxApplied(totalCartTaxApplied);

        return cartDataResponse;

    }

}
