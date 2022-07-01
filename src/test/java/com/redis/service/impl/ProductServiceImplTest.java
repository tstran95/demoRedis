package com.redis.service.impl;

import com.redis.config.JedisPoolFactory;
import com.redis.constant.Constant;
import com.redis.entity.Product;
import com.redis.exception.ProductException;
import com.redis.exception.VNPAYException;
import com.redis.utils.JedisUtil;
import com.redis.utils.MessageUtils;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.JedisPool;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ProductServiceImplTest {
    @Mock
    JedisPoolFactory jedisPoolFactory;

    @Mock
    JedisUtil jedisUtil;

    @InjectMocks
    ProductServiceImpl productServiceImpl;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveProd_Success() {
        Product product = new Product("1110", "Iphone 10", 1, 12333.0);
        assertEquals(productServiceImpl.saveProd(product).getTransactionNo(), product.getTransactionNo());
    }

    @Test
    void saveProd_Fail_SaveMethodError() {
        Product product = new Product("1110", "Iphone 10", 1, 12333.0);
        Mockito.when(jedisUtil.save(any(String.class), any(String.class), any(String.class)))
                                .thenThrow(new RuntimeException("ERROR"));
        Exception exception = assertThrows(ProductException.class, () -> {
            productServiceImpl.saveProd(product);
        });
        assertEquals(exception.getMessage(), MessageUtils.getMessage(Constant.DOUBLE_TRANS));
    }

    @Test
    void saveProd_ProductNull() {
        Product product = null;
        Exception exception = assertThrows(ProductException.class , () -> {
            productServiceImpl.saveProd(product);
        });
        assertEquals(exception.getMessage(), MessageUtils.getMessage(Constant.PROD_NULL));
    }

    @Test
    void saveProd_DuplicateTransNo() {
        Product product = new Product("1115", "Iphone 12", 1, 12333.0);
        Exception exception = assertThrows(ProductException.class, () -> {
            productServiceImpl.saveProd(product);
        });
        assertEquals(exception.getMessage(), MessageUtils.getMessage(Constant.DOUBLE_TRANS));
    }

    @Test
    void saveProd_TransNoNull() {
        Product product = new Product();
        Exception exception = assertThrows(ProductException.class , () -> {
            productServiceImpl.saveProd(product);
        });
        assertEquals(exception.getMessage(), MessageUtils.getMessage(Constant.TRANS_NO_NULL));
    }


    @Test
    void saveProd_CantConnectWithRedis() {
        Mockito.when(jedisPoolFactory.generateJedisPoolFactory()).thenReturn(null);
//        jedisPoolFactory.generateJedisPoolFactory().getResource().close();
        Product product = new Product("1115", "Iphone 12", 1, 12333.0);
        Exception exception = assertThrows(Exception.class, () -> {
            productServiceImpl.saveProd(product);
        });
        assertEquals(exception.getMessage(), MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR));
    }

    @Test
    void getAllProd() {
    }
}