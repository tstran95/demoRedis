package com.redis.service.impl;

import com.redis.constant.Constant;
import com.redis.controller.ProductController;
import com.redis.utils.MessageUtils;
import com.redis.utils.ProductUtils;
import com.redis.entity.Product;
import com.redis.exception.ProductException;
import com.redis.service.ProductService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
    private final RedisTemplate template;
    @Override
    public Product saveProd(Product product) {
        logger.info("ProductServiceImpl saveProd START with request {}" , product );
        try {
            product.setToken(UUID.randomUUID().toString());
            product.setCreatedDate(new Timestamp((new Date()).getTime()).toString());

            Object prod = template.opsForHash().get(Constant.PRODUCT_KEY , product.getTransactionNo());
            if (Objects.nonNull(prod)) {
                throw new ProductException(MessageUtils.getMessage(Constant.NO002));
            }
            template.opsForHash().put(Constant.PRODUCT_KEY , product.getTransactionNo() , product);
            template.expire(Constant.PRODUCT_KEY , Duration.ofSeconds(ProductUtils.getTimeRemaining()));
            logger.info("ProductServiceImpl saveProd END with response {}" , product );
            return product;
        }catch (ParseException e) {
            logger.error("ProductServiceImpl saveProd ERROR with message {}" , e.getMessage() );
            throw new ProductException(Constant.NO003);
        }catch (Exception e) {
            logger.error("ProductServiceImpl saveProd ERROR with message {}" , e.getMessage() );
            throw e;
        }
    }

    @Override
    public List<Product> getAllProd() {
        return template.opsForHash().values(Constant.PRODUCT_KEY);
    }

    @Override
    public Product updateProd(Product product) {
        logger.info("ProductServiceImpl updateProd START with request {}" , product );
        try {
            Product prodOld = this.getByTransNum(product.getTransactionNo());
            product.setCreatedDate(prodOld.getCreatedDate());
            product.setToken(prodOld.getToken());
            product.setUpdateDate(new Timestamp((new Date()).getTime()).toString());
            template.opsForHash().put(Constant.PRODUCT_KEY , product.getTransactionNo() , product);
            logger.info("ProductServiceImpl updateProd END with request {}" , product );
            return product;
        }catch (Exception e) {
            logger.error("ProductServiceImpl updateProd ERROR with message {}" , e.getMessage() );
            throw e;
        }
    }

    @Override
    public Product getByTransNum(String num) {
        logger.info("ProductServiceImpl updateProd START with transactionNo {}" , num );
        Object prod = template.opsForHash().get(Constant.PRODUCT_KEY , num);
        if (Objects.isNull(prod)) throw new ProductException(Constant.NO001);
        logger.info("ProductServiceImpl updateProd END with response {}" , prod );
        return (Product) prod;
    }

    @Override
    public void deleteProd(String num) {
        template.opsForHash().delete(Constant.PRODUCT_KEY , num);
    }
}
