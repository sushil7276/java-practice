package com.java.service;

import com.java.models.Product;
import com.java.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Integer productId) {
        return productRepository.findById(Long.valueOf(productId))
                .orElseThrow(() -> new RuntimeException("User Not Found In DB"));
    }

    @Override
    public Product update(Integer productId, Product product) {
        Product dbProduct = findById(productId);

        dbProduct.setName(product.getName());
        dbProduct.setDescription(product.getDescription());
        dbProduct.setPrice(product.getPrice());
        dbProduct.setStock(product.getStock());

        productRepository.save(dbProduct);

        return dbProduct;
    }

    @Override
    public void delete(Integer productId) {
        productRepository.deleteById(Long.valueOf(productId));
    }
}
