package com.createq.web_shop.facades.impl;

import com.createq.web_shop.converter.ProductConverter;
import com.createq.web_shop.dto.ProductDTO;
import com.createq.web_shop.facades.ProductFacade;
import com.createq.web_shop.model.ProductModel;
import com.createq.web_shop.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DefaultProductFacade implements ProductFacade {
    private final ProductService productService;
    private final ProductConverter productConverter;

    public DefaultProductFacade(ProductService productService, ProductConverter productConverter) {
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @Override
    public List<ProductDTO> getAll() {
        List<ProductModel>products=productService.getAll();
        return productConverter.convertAll(products);
    }

    @Override
    public List<ProductDTO> findByCategoryId(Long id) {
        List<ProductModel>products=productService.getByCategoryId(id);
        return productConverter.convertAll(products);
    }

    @Override
    public List<ProductDTO> getByCategoryOrderByPriceAsc(Long categoryId) {
        List<ProductModel>products=productService.findByCategoryOrderByPriceAsc(categoryId);
        return productConverter.convertAll(products);
    }

    @Override
    public List<ProductDTO> getByCategoryOrderByPriceDesc(Long categoryId) {
        List<ProductModel>products=productService.findByCategoryOrderByPriceDesc(categoryId);
        return productConverter.convertAll(products);
    }

    @Override
    public ProductDTO getById(Long id) {
       ProductModel model=productService.findById(id);
       return productConverter.convert(model);
    }

    @Override
    public List<ProductDTO> getAllByOrderByPriceAsc() {
        List<ProductModel>products=productService.findAllByOrderByPriceAsc();
        return productConverter.convertAll(products);
    }

    @Override
    public List<ProductDTO> getAllByOrderByPriceDesc() {
        List<ProductModel>products=productService.findAllByOrderByPriceDesc();
        return productConverter.convertAll(products);
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        ProductModel model = productConverter.convert(productDTO);
        ProductModel savedModel = productService.save(model);
        return productConverter.convert(savedModel);
    }

    @Override
    public void deleteProduct(Long id) {
        productService.deleteById(id);
    }

    @Override
    public void updateProductPriceAndQuantity(Long id, double price, Integer quantity) {
        productService.updateProductPriceAndQuantity(id,price,quantity);
    }


    public ProductService getProductService() {
        return productService;
    }

    public ProductConverter getProductConverter() {
        return productConverter;
    }
}
