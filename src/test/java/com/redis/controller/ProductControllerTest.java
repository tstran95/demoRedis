package com.redis.controller;

import com.redis.constant.Constant;
import com.redis.entity.Product;
import com.redis.exception.ProductException;
import com.redis.service.ProductService;
import com.redis.utils.MessageUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        productController = new ProductController(productService);
    }

    @Test
    void saveProd_Success() {
        Product product = new Product( "1116" , "Iphone 16", 1, 12333.0 );
        assertEquals(productController.saveProd(product).getMessage() , Constant.SUCCESS);
    }

    @Test
    void saveProd_FailWithProdNull() {
        Mockito.when(productService.saveProd(any())).thenThrow(new ProductException(MessageUtils.getMessage(Constant.PROD_NULL)));
        assertEquals(MessageUtils.getMessage(Constant.PROD_NULL) , productController.saveProd(any()).getMessage());
    }

    @Test
    void saveProd_FailWithDuplicateTransNo() {
        Mockito.when(productService.saveProd(any())).thenThrow(new ProductException(MessageUtils.getMessage(Constant.DOUBLE_TRANS)));
        assertEquals(MessageUtils.getMessage(Constant.DOUBLE_TRANS) , productController.saveProd(any()).getMessage());
    }

    @Test
    void saveProd_FailWithTransNoNull() {
        Mockito.when(productService.saveProd(any())).thenThrow(new ProductException(MessageUtils.getMessage(Constant.TRANS_NO_NULL)));
        assertEquals(MessageUtils.getMessage(Constant.TRANS_NO_NULL) , productController.saveProd(any()).getMessage());
    }

    @Test
    void saveProd_FailWithQuantityIsNegativeNumber() {
        Mockito.when(productService.saveProd(any())).thenThrow(new ProductException(MessageUtils.getMessage(Constant.QTY_WRONG)));
        assertEquals(MessageUtils.getMessage(Constant.QTY_WRONG) , productController.saveProd(any()).getMessage());
    }

    @Test
    void saveProd_FailWithAmountWrong() {
        Mockito.when(productService.saveProd(any())).thenThrow(new ProductException(MessageUtils.getMessage(Constant.AMOUNT_WRONG)));
        assertEquals(MessageUtils.getMessage(Constant.AMOUNT_WRONG) , productController.saveProd(any()).getMessage());
    }
}