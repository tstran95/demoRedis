package com.redis.controller;

import com.redis.constant.Constant;
import com.redis.entity.Product;
import com.redis.reponse.ProductResponse;
import com.redis.service.ProductService;
import com.redis.utils.MessageUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void saveProd_Success() {
        Product product = new Product( "1116" , "Iphone 16", 1, 12333.0 );
        assertEquals(productController.saveProd(product).getMessage() , Constant.SUCCESS);
    }

    @Test
    void saveProd_FailWithProdNull() {
//        Product product = new Product( "1116" , "Iphone 16", 1, 12333.0 );
        assertEquals(MessageUtils.getMessage(Constant.PROD_NULL) , productController.saveProd(null).getMessage());
    }
}