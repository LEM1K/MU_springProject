package com.springproject.mu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/member/login")
    public String printLoginPage() {
        return "member/login";
    }

    @GetMapping("/member/signup")
    public String printSignUpPage() {
        return "member/signup";
    }

    @GetMapping("/board/home")
    public String printMainPage() {
        return "board/home";
    }





}
