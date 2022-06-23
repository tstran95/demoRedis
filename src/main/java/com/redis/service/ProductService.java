package com.redis.service;

import com.redis.entity.Product;

import java.text.ParseException;
import java.util.List;

public interface ProductService {
    Product saveProd(Product product);

    List<Product> getAllProd();

    Product updateProd(Product product);

    Product getByTransNum(String num);

    void deleteProd(String num);
}
