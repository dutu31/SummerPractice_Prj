package com.createq.web_shop.converter;

import com.createq.web_shop.dto.ProductDTO;
import com.createq.web_shop.model.ProductModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {
    public ProductDTO convert(ProductModel product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setImageURL(product.getImageURL());
        if (product.getCategory() != null) {
            dto.setCategoryName(product.getCategory().getName());
        }
        return dto;
    }

    public List<ProductDTO>convertAll(List<ProductModel>productModels) {
        return productModels.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
