package com.clean.architecture.service;

import com.clean.architecture.model.ProductModel;

public interface RedisSvc {

    void putIfAbsen(ProductModel product);

    void put(Long id, ProductModel product);

    Object hashGet();

    Object getById(Long id);

    void delete(Long id);
}
