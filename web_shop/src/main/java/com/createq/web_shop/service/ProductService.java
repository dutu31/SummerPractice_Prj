package com.createq.web_shop.service;

import com.createq.web_shop.model.ProductModel;

import java.util.List;

public interface ProductService {
    List<ProductModel> getAll();
    List<ProductModel> getByCategoryId(Long id);
    List<ProductModel>findByCategoryOrderByPriceAsc(Long categoryId);
    List<ProductModel>findByCategoryOrderByPriceDesc(Long categoryId);
    ProductModel findById(Long id);
    List<ProductModel> findAllByOrderByPriceAsc();
    List<ProductModel> findAllByOrderByPriceDesc();
    public ProductModel save(ProductModel productModel);
    public void deleteById(Long id);

}
