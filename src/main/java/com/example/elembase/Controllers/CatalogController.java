package com.example.elembase.Controllers;


import com.example.elembase.Entitity.Filter;
import com.example.elembase.Entitity.Product;
import com.example.elembase.Services.ProductService;

import com.example.elembase.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/elemBase")
public class CatalogController {


    private final ProductService productService;
    private final ProductRepo productRepo;

    private String choosenProductName = "";

    @Autowired
    public CatalogController(ProductService productService, ProductRepo productRepo) {
        this.productService = productService;
        this.productRepo = productRepo;
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
        if (choosenProductName.isEmpty()) {
            System.out.println("No info about product card");
        } else {
            model.addAttribute("productCardName", choosenProductName);
            HashMap<String, List<String>> statsByNames = productService.getAllProductsStatsByNames();
            model.addAttribute("productStatsNames", productService.getProductStatsNames());
            model.addAttribute("productStats", statsByNames.get(choosenProductName));
            System.out.println("Loading product card");
        }
//        List<Product> sortedProducts = productRepo.findAllByL("0.4", "0.2");
//        System.out.println(sortedProducts.get(1).getL());
        return "catalog";
    }

    @PostMapping("/catalog/useFilter")
    public String catalogFiltered(@RequestParam String l, @RequestParam String w, @RequestParam String operatingTempRange,
                                  @RequestParam String ratedVoltageVDC, @RequestParam String tcCode, @RequestParam String cap,
                                  @RequestParam String tol, @RequestParam String productId, @RequestParam String series,
                                  @RequestParam String chipDimensionsLxW, @RequestParam String heightDimensionT,
                                  @RequestParam String temperatureCharacteristics, @RequestParam String ratedVoltageH,
                                  @RequestParam String capacitance, @RequestParam String capacitanceTolerance,
                                  @RequestParam String individualSpecificationCodeOrLLR, @RequestParam String packing) {

        ArrayList<String> filterParams = new ArrayList<>(Arrays.asList(l, w, operatingTempRange, ratedVoltageVDC, tcCode,
                cap, tol, productId, series, chipDimensionsLxW, heightDimensionT, temperatureCharacteristics, ratedVoltageH,
                capacitance, capacitanceTolerance, individualSpecificationCodeOrLLR, packing));

        //TODO u need to add filter logic here
        System.out.println(l + w + operatingTempRange + ratedVoltageVDC + tcCode +
                cap + tol + productId + series + chipDimensionsLxW + heightDimensionT + temperatureCharacteristics +
                ratedVoltageH + capacitance + capacitanceTolerance + individualSpecificationCodeOrLLR + packing);
        return "redirect:/elemBase/catalog";
    }

    @PostMapping("/catalog/loadProductDescription")
    public String catalogLoadProductDescription(@RequestParam String productName){
        choosenProductName = productName;
        System.out.println(productName);
        return "redirect:/elemBase/catalog";
    }










}
