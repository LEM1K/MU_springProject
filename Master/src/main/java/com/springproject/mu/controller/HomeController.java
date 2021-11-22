package com.springproject.mu.controller;

import com.springproject.mu.model.GeneralBoard;
import com.springproject.mu.repos.GeneralBoardRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private GeneralBoardRepos generalBoardRepos;


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
    public String printMainPage(Model model1, Model model2) {

        List<GeneralBoard> generalBoards = generalBoardRepos.findTop10ByOrderByModifiedTimeDesc();
        model1.addAttribute("generalBoards", generalBoards);


        return "board/home";
    }





}
