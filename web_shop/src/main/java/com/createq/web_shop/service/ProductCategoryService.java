package com.createq.web_shop.service;

import com.createq.web_shop.model.ProductCategoryModel;

import java.util.List;

public interface ProductCategoryService {
List<ProductCategoryModel> getAll();
ProductCategoryModel getById(Long id);
}
