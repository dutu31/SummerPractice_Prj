package com.createq.web_shop.facades;

import com.createq.web_shop.dto.ProductCategoryDTO;

import java.util.List;

public interface ProductCategoryFacade {
    List<ProductCategoryDTO> getAll();
    ProductCategoryDTO getById(Long id);
}
