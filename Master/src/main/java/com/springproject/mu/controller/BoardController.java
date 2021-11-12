package com.springproject.mu.controller;

import com.springproject.mu.dto.GeneralBoardDto;
import com.springproject.mu.dto.MemberDto;
import com.springproject.mu.model.GeneralBoard;
import com.springproject.mu.repos.GeneralBoardRepos;
import com.springproject.mu.service.GeneralBoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BoardController {

    GeneralBoardService generalBoardService;

    GeneralBoardRepos generalBoardRepos;

    @GetMapping("/board/general")
    public String printGeneralPage(Model model, @PageableDefault(size = 10) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {

        //Page<GeneralBoard> generalBoards = generalBoardRepos.findAll(pageable);
        Page<GeneralBoard> generalBoards = generalBoardRepos.findByTitleContaining(searchText, pageable);
        int startpage = Math.max(1, generalBoards.getPageable().getPageNumber() - 4);
        int endpage = Math.min(generalBoards.getTotalPages(), generalBoards.getPageable().getPageNumber() + 4);

        model.addAttribute("startpage", startpage);
        model.addAttribute("endpage", endpage);

        model.addAttribute("generalboards", generalBoards);
        return "/board/general";
    }

    @GetMapping("/board/generaldetail")
    public String generalPostDetail(@RequestParam Long id, Model model) {

       Optional<GeneralBoard> generalBoard = generalBoardRepos.findById(id);

       model.addAttribute("generalBoard", generalBoard.get());

        return "/board/generaldetail";
    }

    @GetMapping("/board/generalform")
    public String generalFormPrint() {
        return "/board/generalform";
    }


    @PostMapping("/board/generalform")
    public String generalFormInsert(@Valid GeneralBoardDto generalBoardDto, Errors errors, Model model, Authentication authentication) {

        String username = authentication.getName();
        model.addAttribute("generalBoardDto", generalBoardDto);

        if(errors.hasErrors()) {

            Map<String, String> validatorResult = generalBoardService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "/board/generalform";
        }


        generalBoardService.insertPost(generalBoardDto, username);

        return "redirect:/board/general";
    }
}
