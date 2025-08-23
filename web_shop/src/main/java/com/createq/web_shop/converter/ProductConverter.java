package com.createq.web_shop.converter;

import com.createq.web_shop.dto.ProductDTO;
import com.createq.web_shop.model.ProductCategoryModel;
import com.createq.web_shop.model.ProductModel;
import com.createq.web_shop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {
    private final ProductCategoryService categoryService;

    @Autowired
    public ProductConverter(ProductCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ProductDTO convert(ProductModel product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setImageURL(product.getImageURL());
        dto.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            dto.setCategoryId((long) product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }
        dto.setQuantity(product.getQuantity());
        dto.setLongDescription(product.getLongDescription());
        dto.setVideoURL(product.getVideoURL());
        return dto;
    }

    public ProductModel convert(ProductDTO dto) {
        ProductModel model = new ProductModel();
        model.setTitle(dto.getTitle());
        model.setDescription(dto.getDescription());
        model.setImageURL(dto.getImageURL());
        model.setPrice(dto.getPrice());
        model.setQuantity(dto.getQuantity());
        model.setLongDescription(dto.getLongDescription());
        model.setVideoURL(dto.getVideoURL());

        if (dto.getCategoryId() != null) {
            ProductCategoryModel category = categoryService.getById(dto.getCategoryId());
            model.setCategory(category);

        } else if(dto.getCategoryName()!=null) {
            ProductCategoryModel category = categoryService.findByName(dto.getCategoryName());
            model.setCategory(category);
        }

        return model;
    }

    public List<ProductDTO>convertAll(List<ProductModel>productModels) {
        return productModels.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
