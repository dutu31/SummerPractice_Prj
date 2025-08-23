package com.createq.web_shop.service.impl;

import com.createq.web_shop.model.ProductModel;
import com.createq.web_shop.repository.ProductRepository;
import com.createq.web_shop.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public ProductModel save(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    @Override
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public void updateProductPriceAndQuantity(Long id, Double price, Integer quantity) {
        ProductModel product=productRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Product not found"));
        product.setPrice(price);
        product.setQuantity(quantity);
        productRepository.save(product);
    }

}
