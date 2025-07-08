package com.createq.web_shop.facades.impl;

import com.createq.web_shop.converter.ProductConverter;
import com.createq.web_shop.dto.ProductDTO;
import com.createq.web_shop.facades.ProductModelFacade;
import com.createq.web_shop.model.ProductModel;
import com.createq.web_shop.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DefaultProductModelFacade implements ProductModelFacade {
    private final ProductService productService;
    private final ProductConverter productConverter;

    public DefaultProductModelFacade(ProductService productService, ProductConverter productConverter) {
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @Override
    public List<ProductDTO> getAll() {
        List<ProductModel>products=productService.getAll();
        return productConverter.convertAll(products);
    }
}
