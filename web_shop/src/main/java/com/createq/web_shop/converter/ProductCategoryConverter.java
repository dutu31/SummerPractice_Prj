package com.createq.web_shop.converter;
import com.createq.web_shop.dto.ProductDTO;
import com.createq.web_shop.dto.ProductCategoryDTO;
import com.createq.web_shop.model.ProductCategoryModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductCategoryConverter {
    private final ProductConverter productConverter;

    public ProductCategoryConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }


    public ProductCategoryDTO convert(ProductCategoryModel productCategoryModel) {
        ProductCategoryDTO dto=new ProductCategoryDTO();
        dto.setId(productCategoryModel.getId());
        dto.setName(productCategoryModel.getName());
        dto.setProducts(productConverter.convertAll(productCategoryModel.getProducts()));
        return dto;
    }


    public List<ProductCategoryDTO>convertAll(List<ProductCategoryModel> productCategoryModelList) {
        return productCategoryModelList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public ProductConverter getProductConverter() {
        return productConverter;
    }
}
