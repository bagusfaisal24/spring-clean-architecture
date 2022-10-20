package com.clean.architecture.controller;

import com.clean.architecture.form.ProductForm;
import com.clean.architecture.service.ProductSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    private final ProductSvc productSvc;
    @Autowired
    public ProductController(ProductSvc productSvc) {
        this.productSvc = productSvc;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ProductForm form){
        return productSvc.create(form);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        return productSvc.getAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Object> getById(@PathVariable  Long id){
        return productSvc.findById(id);
    }

    @PostMapping(value = "{id}")
    public ResponseEntity<Object> update(@RequestBody ProductForm form, @PathVariable Long id){
        return productSvc.updateById(form, id);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        return productSvc.deleteById(id);
    }

}
