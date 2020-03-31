package com.naresh.shoppingcart.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
class CartDataResponseTest {

    @Test
    public void gettersAndSettersShouldWorkForEachProperty() {
        assertThat(CartDataResponse.class, hasValidGettersAndSetters());
    }
}