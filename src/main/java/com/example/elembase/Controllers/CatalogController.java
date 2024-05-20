package com.example.elembase.Controllers;


import com.example.elembase.Entitity.Filter;
import com.example.elembase.Entitity.Order;
import com.example.elembase.Entitity.Product;
import com.example.elembase.Services.OrderService;
import com.example.elembase.Services.ProductService;

import com.example.elembase.configs.MyUserDetails;
import com.example.elembase.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping("/elemBase")
public class CatalogController {


    private final ProductService productService;
    private final ProductRepo productRepo;
    private String choosenProductName = "";
    private boolean filterOn = false;
    private ArrayList<String> filterParams;
    private Boolean userAuthenticated = false;
    private OrderService orderService;

    @Autowired
    public CatalogController(ProductService productService, ProductRepo productRepo, OrderService orderService) {
        this.productService = productService;
        this.productRepo = productRepo;
        this.orderService = orderService;
    }




    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/catalog")
    public String pageForAll(Model model) {
        Iterable<String> products = productService.getProductsCategoryNames();
        model.addAttribute("products", products);
        if (!filterOn){
            List<String> allProductsNames = productService.getAllProductsNames();
            model.addAttribute("allProductsNames", allProductsNames);
        } else {
            Iterable<String> filteredProducts = productService.getFilteredProductsNames(filterParams);
            model.addAttribute("allProductsNames", filteredProducts);
        }

        HashMap<String, List<String>> hashFilters = productService.getHashFilters();
        model.addAttribute("hashFilters", hashFilters);
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
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            MyUserDetails myUserDetails = (MyUserDetails)principal;
            if (Objects.equals(myUserDetails.getUserRole(), "ROLE_USER")){
                userAuthenticated = true;
            } else {
                userAuthenticated = false;
            }
        } catch (Exception e) {
            userAuthenticated = false;
        }


        model.addAttribute("userAuthenticated", userAuthenticated);


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

        filterParams = new ArrayList<>(Arrays.asList(l, w, operatingTempRange, ratedVoltageVDC, tcCode,
                cap, tol, productId, series, chipDimensionsLxW, heightDimensionT, temperatureCharacteristics, ratedVoltageH,
                capacitance, capacitanceTolerance, individualSpecificationCodeOrLLR, packing));

        filterOn = true;

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

    //TODO It is just doesn't work
    @PostMapping("/catalog/addToOrder")
    public String addToCart(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetails myUserDetails = (MyUserDetails)principal;
        Long part_id = productService.getIdByName(choosenProductName);
        Long user_id = myUserDetails.getUserId();
        System.out.println(part_id);
        System.out.println(user_id);
        orderService.createNewOrder(part_id, user_id); //0 - is "order are made" status
        return "redirect:/elemBase/catalog";
    }










}
