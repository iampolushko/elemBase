package com.example.elembase.Controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("elemBase/")
public class UserCabinetController {

    @GetMapping("/userCabinet")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String pageForUser() {
        return "userCabinet";
    }

}
