package com.createq.web_shop.controller;

import com.createq.web_shop.dto.ProductDTO;
import com.createq.web_shop.facades.ProductFacade;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.createq.web_shop.dto.UserDTO;
import com.createq.web_shop.facades.UserFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserFacade userFacade;
    private final ProductFacade productFacade;

    @Autowired
    public AdminController(UserFacade userFacade, ProductFacade productFacade) {
        this.userFacade = userFacade;
        this.productFacade = productFacade;
    }

    @GetMapping
    public String showAdminPage(Model model) {
        List<ProductDTO> products=productFacade.getAll();
        model.addAttribute("products",products);
        model.addAttribute("users", userFacade.getAllUsers());
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("roles", List.of("user", "admin"));
        return "adminPage";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO savedUser = userFacade.registerUser(userDTO);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userFacade.deleteUserById(id);
            return ResponseEntity.ok().build();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404)
                    .body(Map.of("message", "User not found with id: " + id));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("message", "Internal server error"));
        }
    }

    @PostMapping("/promote/{id}")
    public ResponseEntity<?> promoteUser(@PathVariable Long id) {
        try {
            userFacade.updateUserRole(id, "admin");
            return ResponseEntity.ok().build();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("User not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @PostMapping("/product/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
        System.out.println("LONG DESCRIPTION: " + productDTO.getLongDescription());
        try {
            ProductDTO saved = productFacade.addProduct(productDTO);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error"));
        }
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productFacade.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting product");
        }
    }

    @GetMapping("/product/addPage")
    public String showEditProductPage(Model model) {
        model.addAttribute("product",new ProductDTO());
        return "addProduct";
    }

    @GetMapping("/product/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        ProductDTO product = productFacade.getById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

        @PostMapping("/product/edit/{id}")
    @ResponseBody
    public ResponseEntity<?> editProduct(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        try {
            Double price = updates.get("price") != null ? Double.valueOf(updates.get("price").toString()) : null;
            Integer quantity = updates.get("quantity") != null ? Integer.valueOf(updates.get("quantity").toString()) : null;

            if (price == null || quantity == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "Price and quantity are required"));
            }

            productFacade.updateProductPriceAndQuantity(id, price, quantity);

            return ResponseEntity.ok(Map.of("message", "Product updated"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Product not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Internal error"));
        }
    }



}
