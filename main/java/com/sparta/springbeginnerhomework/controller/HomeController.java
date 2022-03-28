package com.sparta.springbeginnerhomework.controller;

import com.sparta.springbeginnerhomework.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if(userDetails == null) {
        }
        else{
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("userLoginCheck", true);
        }

        return "index";
    }
}
