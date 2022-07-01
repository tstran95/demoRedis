package com.redis.controller;

import com.redis.entity.Product;
import com.redis.reponse.ProductResponse;
import com.redis.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    ProductService productService;

    @Test
    void saveProd_Success() {
        Product product = new Product( "1116" , "Iphone 16", 1, 12333.0 );
        assertEquals(productService.saveProd(product).getTransactionNo() , product.getTransactionNo());
    }
}