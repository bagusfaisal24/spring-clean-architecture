package com.clean.architecture.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @GetMapping("server-time")
    public Map<String, Object> getServerTime(){
        Map<String, Object> map = new HashMap<>();
        map.put("time", LocalDateTime.now());
        return map;
    }
}
