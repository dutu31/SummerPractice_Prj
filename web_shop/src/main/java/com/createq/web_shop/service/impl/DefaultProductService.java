package com.createq.web_shop.service.impl;

import com.createq.web_shop.model.ProductModel;
import com.createq.web_shop.repository.ProductRepository;
import com.createq.web_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    @Override
    public List<ProductModel> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductModel> getByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }

    @Override
    public List<ProductModel> findByCategoryOrderByPriceAsc(Long categoryId) {
        return productRepository.findByCategoryIdOrderByPriceAsc(categoryId);
    }

    @Override
    public List<ProductModel> findByCategoryOrderByPriceDesc(Long categoryId) {
        return productRepository.findByCategoryIdOrderByPriceDesc(categoryId);
    }

    @Override
    public ProductModel findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found"));
    }

    @Override
    public List<ProductModel> findAllByOrderByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<ProductModel> findAllByOrderByPriceDesc() {
        return productRepository.findAllByOrderByPriceDesc();
    }
}
