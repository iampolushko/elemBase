package com.example.elembase.Controllers;


import com.example.elembase.Entitity.Filter;
import com.example.elembase.Services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/elemBase")
public class CatalogController {


    private final ProductService productService;

    @Autowired
    public CatalogController(ProductService productService) {
        this.productService = productService;
    }




    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/catalog")
    public String pageForAll(Model model) {
        Iterable<String> products = productService.getProductsCategoryNames();
        HashMap<String, List<String>> hashFilters = productService.getHashFilters();
        List<String> allProductsNames = productService.getAllProductsNames();
        model.addAttribute("products", products);
        model.addAttribute("hashFilters", hashFilters);
        model.addAttribute("allProductsNames", allProductsNames);
        model.addAttribute("filter", new Filter());
        return "catalog";
    }

    //This shit don't work
    @PostMapping("/catalog/useFilter")
    public String catalogFiltered(@ModelAttribute Filter filter) {

            System.out.println(filter);
        return "catalogFiltered";
    }

    @PostMapping("/catalog/addToOrder")
    public String buttonAddToOrder(@RequestParam String firstName, @RequestParam String lastName) {

        System.out.println(firstName + lastName);
        return "catalog";
    }









}