package com.redis.validation;

import com.redis.config.JedisPoolFactory;
import com.redis.constant.Constant;
import com.redis.entity.Product;
import com.redis.exception.ProductException;
import com.redis.exception.VNPAYException;
import com.redis.service.impl.ProductServiceImpl;
import com.redis.utils.JedisUtil;
import com.redis.utils.MessageUtils;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ValidatorTest {
    @InjectMocks
    private Validator validator;
    private Product product = new Product(null, "Iphone 10", -1, 12333.0);

    @BeforeEach
    void setUp() {
        validator = Mockito.mock(Validator.class);
    }

    @Test
    void validateSaveProd_InputTransNoNull() {
        Exception exception = assertThrows(ProductException.class , () -> Validator.validateSaveProd(product));
        assertEquals(exception.getMessage() , MessageUtils.getMessage(Constant.TRANS_NO_NULL));
    }

    @Test
    void validateSaveProd_InputQtyWrong() {
        product.setTransactionNo("123445");
        Exception exception = assertThrows(ProductException.class , () -> Validator.validateSaveProd(product));
        assertEquals(exception.getMessage() , MessageUtils.getMessage(Constant.QTY_WRONG));
    }

    @Test
    void validateTime() {
    }

//    @Test
//    void checkNumber() {
//        Mockito.when(Validator.checkNothing("0")).thenReturn(true);
//        assertFalse(Validator.checkNumber("0"));
//    }
}