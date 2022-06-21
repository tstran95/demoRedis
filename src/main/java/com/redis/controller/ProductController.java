package com.redis.controller;

import com.redis.entity.Product;
import com.redis.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
//    @Cacheable(value = "productCache")
    public Product saveProd(@RequestBody Product product){
        return productService.saveProd(product);
    }

    @GetMapping
    public List<Product> getAllProd() {
        return productService.getAllProd();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProd(@PathVariable Long id) {
        productService.deleteProd(id);
    }
}
