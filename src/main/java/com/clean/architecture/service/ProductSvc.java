package com.clean.architecture.service;

import com.clean.architecture.common.ResponseUtil;
import com.clean.architecture.form.ProductForm;
import com.clean.architecture.model.ProductModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductSvc {

    ResponseEntity<Object> create(ProductForm form);

    ResponseEntity<Object> getAll();

    ResponseEntity<Object> findById(Long id);

    ResponseEntity<Object> updateById(ProductForm form, Long id);

    ResponseEntity<Object> deleteById(Long id);
}
