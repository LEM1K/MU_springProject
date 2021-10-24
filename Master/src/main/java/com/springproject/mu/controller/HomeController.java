package com.springproject.mu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/member/login")
    public String loginPage() {
        return "member/login";
    }

    @GetMapping("/member/signup")
    public String signUpPage() {
        return "member/signup";
    }
}
