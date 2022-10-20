package com.clean.architecture.service;

import com.clean.architecture.config.RedisOperations;
import com.clean.architecture.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisSvcImpl implements RedisSvc{

    private static final String KEY = "product";
    private final RedisOperations redisOperations;

    @Autowired
    public RedisSvcImpl(RedisOperations redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    public void putIfAbsen(ProductModel product) {
        redisOperations.putIfAbsen(KEY, product.getId(), product);
    }

    @Override
    public void put(Long id, ProductModel product) {
        redisOperations.hset(KEY, id, product);
    }

    @Override
    public Object hashGet() {
        return redisOperations.hget(KEY);
    }

    @Override
    public Object getById(Long id) {
        return redisOperations.hget(KEY, id);
    }

    @Override
    public void delete(Long id) {
        redisOperations.deleteByHashKey(KEY, id);
    }
}
