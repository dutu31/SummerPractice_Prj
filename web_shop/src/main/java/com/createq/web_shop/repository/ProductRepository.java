package com.createq.web_shop.repository;

import com.createq.web_shop.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel>findByCategoryId(Long id);

    List<ProductModel> findByCategoryIdOrderByPriceAsc(Long categoryId);

    List<ProductModel> findByCategoryIdOrderByPriceDesc(Long categoryId);
}
