package com.springproject.mu.controller;

import org.springframework.stereotype.Controller;
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

    @GetMapping("/board/general")
    public String printGeneralPage() {
        return "/board/general";
    }

    @GetMapping("/board/column")
    public String printColumnPage() {
        return "/board/column";
    }



}
