package com.createq.web_shop.facades;

import com.createq.web_shop.dto.ProductDTO;
import com.createq.web_shop.model.ProductModel;

import java.util.List;

public interface ProductModelFacade {
    List<ProductDTO> getAll();
}
