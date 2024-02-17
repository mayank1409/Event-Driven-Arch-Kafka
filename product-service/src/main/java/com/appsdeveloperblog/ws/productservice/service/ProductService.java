package com.appsdeveloperblog.ws.productservice.service;

import com.appsdeveloperblog.ws.productservice.model.CreateProductRestModel;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    String save(CreateProductRestModel createProductRestModel) throws ExecutionException, InterruptedException;
}
