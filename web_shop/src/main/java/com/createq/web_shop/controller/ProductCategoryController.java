package com.createq.web_shop.controller;

import com.createq.web_shop.dto.ProductCategoryDTO;
import com.createq.web_shop.facades.ProductCategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductCategoryController {
    private final ProductCategoryFacade productCategoryFacade;

    @Autowired
    public ProductCategoryController(ProductCategoryFacade productCategoryFacade) {
        this.productCategoryFacade = productCategoryFacade;
    }

    @GetMapping("/")
    public String getAll(Model model) {
        List<ProductCategoryDTO> categories=productCategoryFacade.getAll();
        model.addAttribute("categories",categories);
        return "index";
    }
}
