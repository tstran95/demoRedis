package com.redis.service.impl;

import com.redis.constant.Constant;
import com.redis.entity.Product;
import com.redis.exception.ProductException;
import com.redis.service.ProductService;
import com.redis.utils.JedisUtil;
import com.redis.utils.MessageUtils;
import com.redis.utils.ProductUtils;
import com.redis.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final JedisUtil jedisUtil;

    @Override
    public Product saveProd(Product product) {
        log.info("ProductServiceImpl saveProd START with request {}", product);
        try {
            if (Objects.isNull(product)) {
                log.error("ProductServiceImpl saveProd ERROR with message Product null");
                throw new ProductException(MessageUtils.getMessage(Constant.PROD_NULL));
            }
            Validator.validateSaveProd(product);
            product.setToken(UUID.randomUUID().toString());
            product.setCreatedDate(new Timestamp((new Date()).getTime()).toString());
            // isExist???/
            boolean hasProd = jedisUtil.exits(Constant.PRODUCT_KEY, product.getTransactionNo());
            if (hasProd) {
                log.error("ProductServiceImpl saveProd ERROR");
                throw new ProductException(MessageUtils.getMessage(Constant.DOUBLE_TRANS));
            }
            jedisUtil.save(Constant.PRODUCT_KEY, product.getTransactionNo(), product.toString());
            jedisUtil.expire(Constant.PRODUCT_KEY, ProductUtils.getTimeRemaining());
            log.info("ProductServiceImpl saveProd END with response {}", product);
            return product;
        }catch (ProductException e ) {
            log.error("ProductServiceImpl saveProd ERROR with message {}", e.getMessage());
            throw e;
        }
        catch (Exception e) {
            log.error("ProductServiceImpl saveProd ERROR with message {}", e.getMessage());
            throw new ProductException(MessageUtils.getMessage(Constant.INTERNAL_SERVER_ERROR));
        }
    }
//
//    @Override
//    public List<Object> getAllProd() {
//        return  template.opsForHash().values(Constant.PRODUCT_KEY);
//    }
//
//    @Override
//    public Product updateProd(Product product) {
//        log.info("ProductServiceImpl updateProd START with request {}", product);
//        try {
//            Product prodOld = this.getByTransNum(product.getTransactionNo());
//            product.setCreatedDate(prodOld.getCreatedDate());
//            product.setToken(prodOld.getToken());
//            product.setUpdateDate(new Timestamp((new Date()).getTime()).toString());
//            template.opsForHash().put(Constant.PRODUCT_KEY, product.getTransactionNo(), product);
//            log.info("ProductServiceImpl updateProd END with request {}", product);
//            return product;
//        } catch (Exception e) {
//            log.error("ProductServiceImpl updateProd ERROR with message ", e);
//            throw e;
//        }
//    }
//
//    @Override
//    public Product getByTransNum(String num) {
//        log.info("ProductServiceImpl updateProd START with transactionNo {}", num);
//        Object prod = template.opsForHash().get(Constant.PRODUCT_KEY, num);
//        if (Objects.isNull(prod)) throw new ProductException(Constant.PROD_NOT_FOUND);
//        log.info("ProductServiceImpl updateProd END with response {}", prod);
//        return (Product) prod;
//    }
//
//    @Override
//    public void deleteProd(String num) {
//        template.opsForHash().delete(Constant.PRODUCT_KEY, num);
//    }
}
