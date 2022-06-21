package com.redis.service;

import com.redis.entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProd(Product product);

    List<Product> getAllProd();

    Product updateProd(Product product);

    Product getById(Long id);

    void deleteProd(Long id);
}
