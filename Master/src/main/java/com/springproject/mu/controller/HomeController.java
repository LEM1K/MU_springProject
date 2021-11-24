package com.springproject.mu.controller;

import com.springproject.mu.model.ColumnBoard;
import com.springproject.mu.model.GeneralBoard;
import com.springproject.mu.repos.ColumnBoardRepos;
import com.springproject.mu.repos.GeneralBoardRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private GeneralBoardRepos generalBoardRepos;

    @Autowired
    private ColumnBoardRepos columnBoardRepos;


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
        List<ColumnBoard> columnBoards = columnBoardRepos.findTop10ByOrderByModifiedTimeDesc();

        model1.addAttribute("generalBoards", generalBoards);
        model2.addAttribute("columnBoards", columnBoards);
        

        return "board/home";
    }





}
