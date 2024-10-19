package com.example.demo.controller;

import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Map;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        String apiUrl = "https://api.example.com/products";  // API URL을 여기에 설정
        try {
            Map<String, Object> productData = productService.getProductData(apiUrl);
            model.addAttribute("product", productData);
        } catch (IOException e) {
            model.addAttribute("error", "Failed to fetch product data.");
        }
        return "products";
    }
}
