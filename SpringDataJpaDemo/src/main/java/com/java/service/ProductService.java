package com.java.service;

import com.java.models.Product;

public interface ProductService {
    Product save(Product product);
    Product findById(Integer productId);
    Product update(Integer productId,Product product);
    void delete(Integer productId);
}
