package com.createq.web_shop.controller;

import com.createq.web_shop.dto.ProductCategoryDTO;
import com.createq.web_shop.dto.ProductDTO;
import com.createq.web_shop.facades.ProductCategoryFacade;
import com.createq.web_shop.facades.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class ProductController {
    private final ProductFacade productModelFacade;
    private final ProductCategoryFacade productCategoryFacade;

    @Autowired
    public ProductController(ProductFacade productModelFacade, ProductCategoryFacade productCategoryFacade) {
        this.productModelFacade = productModelFacade;
        this.productCategoryFacade = productCategoryFacade;

    }

    @GetMapping("/products")
    public String getAllProducts(@RequestParam(required = false, name="sort") String sort, Model model) {
        List<ProductDTO> products;
        if("priceAsc".equalsIgnoreCase(sort)) {
            products=productModelFacade.getAllByOrderByPriceAsc();
        }
        else if("priceDesc".equalsIgnoreCase(sort)) {
            products=productModelFacade.getAllByOrderByPriceDesc();
        }
        else {
            products=productModelFacade.getAll();
        }
        model.addAttribute("products", products);
        return "allProducts";
    }

    @GetMapping("/category/{id}/products")
    public String getProductsByCategory(@PathVariable Long id, @RequestParam(required = false,name="sort") String sort, Model model) {
        ProductCategoryDTO category=productCategoryFacade.getById(id);
        if(category==null) {
            model.addAttribute("errorMessage","NO CATEGORY FOUND !");
        }
        else {
            List<ProductDTO> products = productModelFacade.findByCategoryId(id);
            if("priceAsc".equalsIgnoreCase(sort)) {
                products=productModelFacade.getByCategoryOrderByPriceAsc(id);
            }
            else if("priceDesc".equalsIgnoreCase(sort)) {
                products=productModelFacade.getByCategoryOrderByPriceDesc(id);
            }
            else {
                products=productModelFacade.findByCategoryId(id);
            }
            if (products == null || products.isEmpty()) {
                model.addAttribute("errorMessage", "NO PRODUCTS IN THIS CATEGORY !");
                return "error";
            }
            model.addAttribute("products", products);
        }
        return "allProducts";
    }

    @GetMapping("/product/{id}/details")
    public String getProductDetails(@PathVariable Long id, Model model) {
        ProductDTO product = productModelFacade.getById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        model.addAttribute("product", product);
        return "productDetails";
    }



    public ProductFacade getProductModelFacade() {
        return productModelFacade;
    }
}
