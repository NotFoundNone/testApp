package com.example.testapp.controllers;

import com.example.testapp.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private List<Product> products = new ArrayList<>();

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/create")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "create-product";
    }

    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        products.add(product);
        return "redirect:/products";
    }
}
