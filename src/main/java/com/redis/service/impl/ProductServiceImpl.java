package com.redis.service.impl;

import com.redis.entity.Product;
import com.redis.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    public static final String PRODUCT_KEY = "Product";
    private final RedisTemplate template;

    @Override
    public Product saveProd(Product product) {
        template.opsForHash().put(PRODUCT_KEY , product.getId() , product);
        return product;
    }

    @Override
    public List<Product> getAllProd() {
        return template.opsForHash().values(PRODUCT_KEY);
    }

    @Override
    public Product updateProd(Product product) {
        return null;
    }

    @Override
    public Product getById(Long id) {
        return (Product) template.opsForHash().get(PRODUCT_KEY , id);
    }

    @Override
    public void deleteProd(Long id) {
        template.opsForHash().delete(PRODUCT_KEY , id);
    }
}
