package com.createq.web_shop.service.impl;

import com.createq.web_shop.model.ProductCategoryModel;
import com.createq.web_shop.repository.ProductCategoryRepository;
import com.createq.web_shop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductCategoryService implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public DefaultProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }
    public List<ProductCategoryModel> getAll() {
        return productCategoryRepository.findAll();
    }

    public ProductCategoryRepository getProductCategoryRepository() {
        return productCategoryRepository;
    }
}
