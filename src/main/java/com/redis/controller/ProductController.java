package com.redis.controller;

import com.redis.constant.Constant;
import com.redis.entity.Product;
import com.redis.reponse.ProductResponse;
import com.redis.service.ProductService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
public class ProductController {
    private static final Logger logger = LogManager.getLogger(ProductController.class);
    private final ProductService productService;

    @PostMapping
    public ProductResponse saveProd(@RequestBody Product product) {
        logger.info("ProductController saveProd START with request {}" , product );
        ProductResponse response = new ProductResponse();
        try {
            productService.saveProd(product);
            response.setRCode(Constant.R_00);
            response.setMessage(Constant.SUCCESS);
            logger.info("ProductController saveProd END with response {}" , response );
        } catch (Exception e) {
            response.setRCode(Constant.R_01);
            response.setMessage(e.getMessage());
            logger.error("ProductController saveProd error with message {}" , e.getMessage() );
        }
        return response;
    }

    @GetMapping
    public List<Product> getAllProd() {
        logger.info("ProductController getAllProd START " );
        return productService.getAllProd();
    }

    @GetMapping("/{num}")
    public ProductResponse getByTransNum(@PathVariable String num) {
        logger.info("ProductController getByTransNum START with request {}" , num );
        ProductResponse response = new ProductResponse();
        try {
            productService.getByTransNum(num);
            response.setRCode(Constant.R_00);
            response.setMessage(Constant.SUCCESS);
            logger.info("ProductController getByTransNum END with response {}" , response );
        } catch (Exception e) {
            response.setRCode(Constant.R_01);
            response.setMessage(e.getMessage());
            logger.error("ProductController getByTransNum ERROR with message {}" , e.getMessage() );
        }
        return response;
    }

    @DeleteMapping("/{num}")
    public void deleteProd(@PathVariable String num) {
        productService.deleteProd(num);
    }

    @PutMapping
    public ProductResponse updateProd(@RequestBody Product product) {
        logger.info("ProductController updateProd START with request {}" , product );
        ProductResponse response = new ProductResponse();
        try {
            productService.updateProd(product);
            response.setRCode(Constant.R_00);
            response.setMessage(Constant.SUCCESS);
            logger.info("ProductController updateProd END with response {}" , response );
        } catch (Exception e) {
            response.setRCode(Constant.R_01);
            response.setMessage(e.getMessage());
            logger.error("ProductController updateProd ERROR with message {}" , e.getMessage() );
        }
        return response;
    }
}
