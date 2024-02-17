package com.appsdeveloperblog.ws.productservice.controller;

import com.appsdeveloperblog.ws.productservice.exception.ErrorMessage;
import com.appsdeveloperblog.ws.productservice.model.CreateProductRestModel;
import com.appsdeveloperblog.ws.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductRestModel productRestModel) {
        String productId = null;
        try {
            productId = productService.save(productRestModel);
        } catch (Exception e) {
//          e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                     new ErrorMessage(System.currentTimeMillis(), e.getMessage(), "/products"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
