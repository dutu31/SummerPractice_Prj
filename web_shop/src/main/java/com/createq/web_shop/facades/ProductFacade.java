package com.createq.web_shop.facades;

import com.createq.web_shop.dto.ProductDTO;
import com.createq.web_shop.model.ProductModel;

import java.util.List;

public interface ProductFacade {
    List<ProductDTO> getAll();
    List<ProductDTO>findByCategoryId(Long id);
    List<ProductDTO>getByCategoryOrderByPriceAsc(Long categoryId);
    List<ProductDTO>getByCategoryOrderByPriceDesc(Long categoryId);
    ProductDTO getById(Long id);
    List<ProductDTO>getAllByOrderByPriceAsc();
    List<ProductDTO>getAllByOrderByPriceDesc();
    ProductDTO addProduct(ProductDTO productDTO);
    public void deleteProduct(Long id);
    public void updateProductPriceAndQuantity(Long id, double price, Integer quantity);

}
