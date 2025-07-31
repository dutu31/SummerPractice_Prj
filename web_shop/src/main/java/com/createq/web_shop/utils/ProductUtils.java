package com.createq.web_shop.utils;

import com.createq.web_shop.dto.ProductDTO;
import com.createq.web_shop.facades.ProductFacade;
import com.createq.web_shop.model.ProductModel;

import java.util.List;

public class ProductUtils {
    public static List<ProductDTO> getProductsBySort(Long id, String sort, ProductFacade productModelFacade) {
        if("priceAsc".equalsIgnoreCase(sort)) {
           return productModelFacade.getByCategoryOrderByPriceAsc(id);
        }
        else if("priceDesc".equalsIgnoreCase(sort)) {
           return productModelFacade.getByCategoryOrderByPriceDesc(id);
        }
        else {
            return productModelFacade.findByCategoryId(id);
        }
    }

    public static List<ProductDTO>getAllProductsBySort(String sort, ProductFacade productModelFacade) {
        if("priceAsc".equalsIgnoreCase(sort)) {
            return productModelFacade.getAllByOrderByPriceAsc();
        }
        else if("priceDesc".equalsIgnoreCase(sort)) {
            return productModelFacade.getAllByOrderByPriceDesc();
        }
        else {
            return productModelFacade.getAll();
        }
    }
}
