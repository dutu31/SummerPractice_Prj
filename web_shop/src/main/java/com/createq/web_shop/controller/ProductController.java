package com.createq.web_shop.controller;

import com.createq.web_shop.dto.ProductDTO;
import com.createq.web_shop.facades.ProductModelFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    private final ProductModelFacade productModelFacade;

    @Autowired
    public ProductController(ProductModelFacade productModelFacade) {
        this.productModelFacade = productModelFacade;
    }
    @GetMapping("/category/{id}/products")
    public String getAll(Model model){
        List<ProductDTO> products=productModelFacade.getAll();
        model.addAttribute("products",products);
        return "allProducts";
    }



}
