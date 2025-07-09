package com.createq.web_shop.controller;

import com.createq.web_shop.dto.ProductDTO;
import com.createq.web_shop.facades.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {
    private final ProductFacade productModelFacade;

    @Autowired
    public ProductController(ProductFacade productModelFacade) {
        this.productModelFacade = productModelFacade;
    }
    @GetMapping("/category/{id}/products")
    public String getProductsByCategory(@PathVariable Long id,Model model) {
        List<ProductDTO>products=productModelFacade.findByCategoryId(id);
        model.addAttribute("products",products);
        return "allProducts";
    }



    public ProductFacade getProductModelFacade() {
        return productModelFacade;
    }
}
