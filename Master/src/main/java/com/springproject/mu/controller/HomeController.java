package com.springproject.mu.controller;

import com.springproject.mu.model.Member;
import com.springproject.mu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private MemberService memberService;

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

    @GetMapping("/main")
    public String printMainPage() {
        return "main";
    }


}
