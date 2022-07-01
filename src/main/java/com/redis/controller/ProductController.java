package com.redis.controller;

import com.redis.constant.Constant;
import com.redis.entity.Product;
import com.redis.reponse.ProductResponse;
import com.redis.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductResponse saveProd(@RequestBody Product product) {
        log.info("ProductController saveProd START with request {}", product);
        ProductResponse response = new ProductResponse();
        try {
            productService.saveProd(product);
            response.setRCode(Constant.RCODE_00);
            response.setMessage(Constant.SUCCESS);
            log.info("ProductController saveProd END with response {}", response);
        } catch (Exception e) {
            response.setRCode(Constant.RCODE_01);
            response.setMessage(e.getMessage());
            log.error("ProductController saveProd error with message ", e);
        }
        return response;
    }
//
//    @GetMapping
//    public List<Object> getAllProd() {
//        log.info("ProductController getAllProd START ");
//        return productService.getAllProd();
//    }
//
//    @GetMapping("/{num}")
//    public ProductResponse getByTransNum(@PathVariable String num) {
//        log.info("ProductController getByTransNum START with request {}", num);
//        ProductResponse response = new ProductResponse();
//        try {
//            productService.getByTransNum(num);
//            response.setRCode(Constant.RCODE_00);
//            response.setMessage(Constant.SUCCESS);
//            log.info("ProductController getByTransNum END with response {}", response);
//        } catch (Exception e) {
//            response.setRCode(Constant.RCODE_01);
//            response.setMessage(e.getMessage());
//            log.error("ProductController getByTransNum ERROR with message ", e);
//        }
//        return response;
//    }
//
//    @DeleteMapping("/{num}")
//    public void deleteProd(@PathVariable String num) {
//        productService.deleteProd(num);
//    }
//
//    @PutMapping
//    public ProductResponse updateProd(@RequestBody Product product) {
//        log.info("ProductController updateProd START with request {}", product);
//        ProductResponse response = new ProductResponse();
//        try {
//            productService.updateProd(product);
//            response.setRCode(Constant.RCODE_00);
//            response.setMessage(Constant.SUCCESS);
//            log.info("ProductController updateProd END with response {}", response);
//        } catch (Exception e) {
//            response.setRCode(Constant.RCODE_01);
//            response.setMessage(e.getMessage());
//            log.error("ProductController updateProd ERROR with message ", e);
//        }
//        return response;
//    }
}
