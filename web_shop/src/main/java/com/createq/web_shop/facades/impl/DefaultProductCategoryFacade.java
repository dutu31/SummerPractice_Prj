package com.createq.web_shop.facades.impl;

import com.createq.web_shop.converter.ProductCategoryConverter;
import com.createq.web_shop.dto.ProductCategoryDTO;
import com.createq.web_shop.facades.ProductCategoryFacade;
import com.createq.web_shop.model.ProductCategoryModel;
import com.createq.web_shop.service.ProductCategoryService;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DefaultProductCategoryFacade implements ProductCategoryFacade {
    private final ProductCategoryService productCategorySerivce;
    private final ProductCategoryConverter productCategoryConverter;

    public DefaultProductCategoryFacade(ProductCategoryService productCategorySerivce, ProductCategoryConverter productCategoryConverter) {
        this.productCategorySerivce = productCategorySerivce;
        this.productCategoryConverter = productCategoryConverter;
    }


    @Override
    public List<ProductCategoryDTO> getAll() {
        List< ProductCategoryModel>productCategoryModelList=productCategorySerivce.getAll();
        return productCategoryConverter.convertAll(productCategoryModelList);
    }
}
