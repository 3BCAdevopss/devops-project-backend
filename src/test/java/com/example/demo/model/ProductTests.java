package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;

class ProductTests {

    @Test
    void calculateProfitAndMarginSetsValues() throws Exception {
        Product product = new Product();
        product.setCostPrice(50.0);
        product.setSellingPrice(75.0);

        Method method = Product.class.getDeclaredMethod("calculateProfitAndMargin");
        method.setAccessible(true);
        method.invoke(product);

        assertEquals(25.0, product.getProfit());
        assertEquals(50.0, product.getMargin());
    }
}
