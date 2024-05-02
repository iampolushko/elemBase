package com.example.elembase.Controllers;


import com.example.elembase.Entitity.User;
import com.example.elembase.Services.ProductService;
import com.example.elembase.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/elemBase")
public class AdminPanelController {


    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public AdminPanelController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }


    @GetMapping("/adminCabinetLists")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String pageForAdminCabinetLists(Model model) {
        List<String> allProductsNames = productService.getAllProductsNames();
        List<String> allUsersNames =  userService.getAllUsersNames();
        model.addAttribute("allProductsNames", allProductsNames);
        model.addAttribute("allUsersNames", allUsersNames);
        model.addAttribute("listsPage", Boolean.TRUE);
        model.addAttribute("editPage", Boolean.FALSE);
        model.addAttribute("statisticPage", Boolean.FALSE);
        return "adminCabinetLists";
    }

    @GetMapping("/adminCabinetEdit")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String pageForAdminCabinetEdit(Model model) {
        model.addAttribute("listsPage", Boolean.FALSE);
        model.addAttribute("editPage", Boolean.TRUE);
        model.addAttribute("statisticPage", Boolean.FALSE);
        return "adminCabinetEdit";
    }
    @PostMapping("/adminCabinetEdit/edit")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewUser(@RequestParam String name,
                                        @RequestParam String password, @RequestParam String  role) {
        User user = new User(null, name, password, role);
        System.out.println(user.toString());
        userService.createNewUser(user);
        return "adminCabinetEdit"; //return statement here is broken. Try to use redirect
    }


    @GetMapping("/adminCabinetStatistics")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String pageForAdminCabinetStatistic(Model model) {
        model.addAttribute("listsPage", Boolean.FALSE);
        model.addAttribute("editPage", Boolean.FALSE);
        model.addAttribute("statisticPage", Boolean.TRUE);
        return "adminCabinetStatistics";
    }

    




}
