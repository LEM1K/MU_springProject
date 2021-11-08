package com.springproject.mu.controller;

import com.springproject.mu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/board/composite")
    public String printCompositePage() {
        return "/board/composite";
    }


}
