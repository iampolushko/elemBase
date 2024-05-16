package com.example.elembase.Controllers;


import com.example.elembase.Entitity.Order;
import com.example.elembase.Entitity.User;
import com.example.elembase.Services.OrderService;
import com.example.elembase.Services.ProductService;
import com.example.elembase.Services.UserService;
import com.example.elembase.configs.MyUserDetails;
import com.example.elembase.repository.OrderRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("elemBase/")
public class UserCabinetController {

    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderRepo orderRepo;
    @Autowired
    public UserCabinetController(UserService userService, ProductService productService, OrderService orderService, OrderRepo orderRepo){
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
        this.orderRepo = orderRepo;
    }

    @GetMapping("/userCabinet")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String pageForUser(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        model.addAttribute("name", authentication.getName());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetails myUserDetails = (MyUserDetails)principal;
        model.addAttribute("name", myUserDetails.getUsername());
        model.addAttribute("id", myUserDetails.getUserId());
        List<String> userOrdersNames = new ArrayList<>();
        for (Long productId : orderService.getProductsId(myUserDetails.getUserId())) {
            userOrdersNames.add(productService.getNameById(productId));
        }
        model.addAttribute("userOrdersNames", userOrdersNames);


        return "userCabinet";
    }


    @PostMapping("/userCabinet/logout")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String logout(HttpServletRequest request) {
        System.out.println("Test");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/elemBase/catalog";
    }


}
