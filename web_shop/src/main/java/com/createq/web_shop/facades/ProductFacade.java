package com.createq.web_shop.facades;

import com.createq.web_shop.dto.ProductDTO;

import java.util.List;

public interface ProductFacade {
    List<ProductDTO> getAll();
    List<ProductDTO>findByCategoryId(Long id);
}
