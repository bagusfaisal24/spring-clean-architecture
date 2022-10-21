package com.clean.architecture.service;

import com.clean.architecture.config.RedisOperations;
import com.clean.architecture.model.ProductModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisSvcImpl implements RedisSvc {

    private static final String KEY = "product";
    private final RedisOperations redisOperations;

    @Autowired
    public RedisSvcImpl(RedisOperations redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    public void putIfAbsen(ProductModel product) {
        try {
            redisOperations.putIfAbsen(KEY, product.getId(), product);
        } catch (Exception e) {
            log.error("error put if absen {}", e.getMessage());
        }

    }

    @Override
    public void put(Long id, ProductModel product) {
        try {
            redisOperations.hset(KEY, id, product);
        } catch (Exception e) {
            log.error("error put {}", e.getMessage());
        }
    }

    @Override
    public Object hashGet() {
        try {
            return redisOperations.hget(KEY);
        } catch (Exception e) {
            log.error("error get {}", e.getMessage());
        }
        return null;
    }

    @Override
    public Object getById(Long id) {
        try {
            return redisOperations.hget(KEY, id);
        } catch (Exception e) {
            log.error("error get by id {}", e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            redisOperations.deleteByHashKey(KEY, id);
        } catch (Exception e) {
            log.error("error delete {}", e.getMessage());
        }
    }
}
