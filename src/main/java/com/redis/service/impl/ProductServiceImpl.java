package com.redis.service.impl;

import com.redis.utils.ProductUtils;
import com.redis.entity.Product;
import com.redis.exception.ProductException;
import com.redis.service.ProductService;
import lombok.AllArgsConstructor;
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
    public static final String PRODUCT_KEY = "Product";
    private final RedisTemplate template;

//    private final ProductRepository productRepository;

    @Override
    public Product saveProd(Product product) {
        try {
            //checkALL()
            product.setToken(UUID.randomUUID().toString());
            product.setCreatedDate(new Timestamp((new Date()).getTime()).toString());

            Object prod = template.opsForHash().get(PRODUCT_KEY , product.getTransactionNo());
            if (Objects.nonNull(prod)) {
                throw new ProductException("Duplicate transaction!!!!");
            }
            template.opsForHash().put(PRODUCT_KEY , product.getTransactionNo() , product);
            template.expire(PRODUCT_KEY , Duration.ofSeconds(ProductUtils.getTimeRemaining()));

            return product;
        }catch (ParseException e) {
            throw new ProductException("Cant Parse!!!!");
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Product> getAllProd() {
        return template.opsForHash().values(PRODUCT_KEY);
    }

    @Override
    public Product updateProd(Product product) {
        try {
            Product prodOld = this.getByTransNum(product.getTransactionNo());
            product.setCreatedDate(prodOld.getCreatedDate());
            product.setToken(prodOld.getToken());
            product.setUpdateDate(new Timestamp((new Date()).getTime()).toString());
            template.opsForHash().put(PRODUCT_KEY , product.getTransactionNo() , product);
            return product;
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Product getByTransNum(String num) {
        Object prod = template.opsForHash().get(PRODUCT_KEY , num);
        if (Objects.isNull(prod)) throw new ProductException("Product not found!!!!");
        return (Product) prod;
    }

    @Override
    public void deleteProd(String num) {
        template.opsForHash().delete(PRODUCT_KEY , num);
    }
}
