package com.redis.controller;

import com.redis.entity.Product;
import com.redis.reponse.ProductResponse;
import com.redis.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponse saveProd(@RequestBody Product product) {
        ProductResponse response = new ProductResponse();
        try {
            productService.saveProd(product);
            response.setRCode("00");
            response.setMessage("Success!!!");

        } catch (Exception e) {
            response.setRCode("01");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping
    public List<Product> getAllProd() {
        return productService.getAllProd();
    }

    @GetMapping("/{num}")
    public ProductResponse getByTransNum(@PathVariable String num) {

        ProductResponse response = new ProductResponse();
        try {
            productService.getByTransNum(num);
            response.setRCode("00");
            response.setMessage("Success!!!");
        } catch (Exception e) {
            response.setRCode("01");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/{num}")
    public void deleteProd(@PathVariable String num) {
        productService.deleteProd(num);
    }

    @PutMapping
    public ProductResponse updateProd(@RequestBody Product product) {
        ProductResponse response = new ProductResponse();
        try {
            productService.updateProd(product);
            response.setRCode("00");
            response.setMessage("Success!!!");

        } catch (Exception e) {
            response.setRCode("01");
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
