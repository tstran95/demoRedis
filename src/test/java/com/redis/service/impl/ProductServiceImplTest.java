package com.redis.service.impl;

import com.redis.config.JedisPoolFactory;
import com.redis.constant.Constant;
import com.redis.entity.Product;
import com.redis.exception.ProductException;
import com.redis.utils.JedisUtil;
import com.redis.utils.MessageUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private JedisPoolFactory jedisPoolFactory;

    @Mock
    private JedisUtil jedisUtil;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setUp() {
        jedisUtil = Mockito.mock(JedisUtil.class);
        jedisPoolFactory = Mockito.mock(JedisPoolFactory.class);
        productServiceImpl = new ProductServiceImpl(jedisUtil);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void saveProd_Success() {
        Product product = new Product("1115", "Iphone 10", 1, 12333.0);
        assertEquals(productServiceImpl.saveProd(product).getTransactionNo(), product.getTransactionNo());
    }

    @Test
    @Order(2)
    void saveProd_Fail_SaveMethodInRedisError() {
        Product product = new Product("1110", "Iphone 10", 1, 12333.0);
        Mockito.when(jedisUtil.save(any(), any(), any()))
                                .thenThrow(new ProductException(MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR)));
        Exception exception = assertThrows(ProductException.class, () -> productServiceImpl.saveProd(product));
        assertEquals(exception.getMessage(), MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR));
    }

    @Test
    @Order(3)
    void saveProd_Fail_ExistMethodInRedisError() {
        Product product = new Product("1110", "Iphone 10", 1, 12333.0);
        Mockito.when(jedisUtil.exits(any(), any()))
                .thenThrow(new ProductException(MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR)));
        Exception exception = assertThrows(ProductException.class, () -> productServiceImpl.saveProd(product));
        assertEquals(exception.getMessage(), MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR));
    }

    @Test
    @Order(4)
    void saveProd_Fail_ExpireMethodInRedisError() {
        Product product = new Product("1110", "Iphone 10", 1, 12333.0);
        Mockito.when(jedisUtil.expire(any(), any()))
                .thenThrow(new ProductException(MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR)));
        Exception exception = assertThrows(ProductException.class, () -> productServiceImpl.saveProd(product));
        assertEquals(exception.getMessage(), MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR));
    }


    @Test
    @Order(5)
    void saveProd_ProductNull() {
//        Product product = null;
        Exception exception = assertThrows(ProductException.class , () -> productServiceImpl.saveProd(null));
        assertEquals(exception.getMessage(), MessageUtils.getMessage(Constant.PROD_NULL));
    }

    @Test
    @Order(6)
    void saveProd_DuplicateTransNo() {
        Product product = new Product("1115", "Iphone 12", 1, 12333.0);
        Exception exception = assertThrows(ProductException.class, () -> productServiceImpl.saveProd(product));
        assertEquals(exception.getMessage(), MessageUtils.getMessage(Constant.DOUBLE_TRANS));
    }

    @Test
    @Order(7)
    void saveProd_TransNoNull() {
        Product product = new Product();
        Exception exception = assertThrows(ProductException.class , () -> {
            productServiceImpl.saveProd(product);
        });
        assertEquals(exception.getMessage(), MessageUtils.getMessage(Constant.TRANS_NO_NULL));
    }

    @Test
    @Order(8)
    public void saveProd_QuantityIsNegativeNumber() {
        Product product = new Product("1233" ,"Iphone 11", -1 , 123.0);
        Exception exception = assertThrows(ProductException.class , () -> {
            productServiceImpl.saveProd(product);
        });
        assertEquals(exception.getMessage(), MessageUtils.getMessage(Constant.QTY_WRONG));
    }


//    @Test
//    @Order(7)
//    void saveProd_CantConnectWithRedis() {
//        Mockito.when(jedisPoolFactory.generateJedisPoolFactory()).thenReturn(null);
////        Mockito.when(jedisPoolFactory.generateJedisPoolFactory()).thenReturn(null);
//        Product product = new Product("1115", "Iphone 12", 1, 12333.0);
//        Exception exception = assertThrows(Exception.class, () -> productServiceImpl.saveProd(product));
//        assertEquals(exception.getMessage(), MessageUtils.getMessage(Constant.CONNECT_REDIS_ERROR));
//    }
}