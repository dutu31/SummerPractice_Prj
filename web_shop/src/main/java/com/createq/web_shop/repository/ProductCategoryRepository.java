package com.createq.web_shop.repository;

import com.createq.web_shop.model.ProductCategoryModel;
import com.createq.web_shop.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategoryModel, Long> {
}
