package com.example.elembase.Controllers;


import com.example.elembase.Entitity.Order;
import com.example.elembase.Entitity.User;
import com.example.elembase.Services.OrderService;
import com.example.elembase.Services.ProductService;
import com.example.elembase.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/elemBase")
public class AdminPanelController {


    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;
    @Autowired
    public AdminPanelController(ProductService productService, UserService userService, OrderService orderService) {
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
    }


    @GetMapping("/adminCabinetLists")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String pageForAdminCabinetLists(Model model) {
        List<String> allProductsNames = productService.getAllProductsNames();
        List<String> allUsersNames =  userService.getAllUsersNames();
        model.addAttribute("allProductsNames", allProductsNames);
        model.addAttribute("allUsersNames", allUsersNames);

        List<Order> orders = orderService.getAllOrders();
        List<String> ordersOutputs = new ArrayList<>();
        for (Order order : orders) {
            ordersOutputs.add("Id: " + String.valueOf(order.getId()) + ", Name: " + productService.getNameById(order.getIdProduct()));
        }
        model.addAttribute("ordersOutputs", ordersOutputs);

        model.addAttribute("listsPage", Boolean.TRUE);
        model.addAttribute("editPage", Boolean.FALSE);
        model.addAttribute("statisticPage", Boolean.FALSE);
        return "adminCabinetLists";
    }

    @GetMapping("/adminCabinetEdit")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String pageForAdminCabinetEdit(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("productService", productService);
        model.addAttribute("listsPage", Boolean.FALSE);
        model.addAttribute("editPage", Boolean.TRUE);
        model.addAttribute("statisticPage", Boolean.FALSE);
        return "adminCabinetEdit";
    }
    @PostMapping("/adminCabinetEdit/edit")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addNewUser(@RequestParam String name,
                                        @RequestParam String password, @RequestParam String  role) {
        //TODO You need to encrypt the password
        userService.createNewUser(name, password, role);
        return "redirect:/elemBase/adminCabinetEdit";
    }

    @PostMapping("/adminCabinetEdit/editOrderStatus")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String editOrderStatus(@RequestParam Long orderId) {
        if (orderService.getOrderById(orderId).getStatus() == 0L) {
            orderService.updateStatus(1L, orderId);
        } else {
            orderService.updateStatus(0L, orderId);
        }

        return "redirect:/elemBase/adminCabinetEdit";
    }


    @GetMapping("/adminCabinetStatistics")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String pageForAdminCabinetStatistic(Model model) {

        model.addAttribute("productsQuantity", String.valueOf(productService.getAllProducts().size()));
        model.addAttribute("usersQuantity", String.valueOf(userService.getAllUsersNames().size()));
        model.addAttribute(" ", String.valueOf(orderService.getAllOrders().size()));



        model.addAttribute("listsPage", Boolean.FALSE);
        model.addAttribute("editPage", Boolean.FALSE);
        model.addAttribute("statisticPage", Boolean.TRUE);
        return "adminCabinetStatistics";
    }

    




}
