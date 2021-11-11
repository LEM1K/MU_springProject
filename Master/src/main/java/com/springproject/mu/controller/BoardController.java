package com.springproject.mu.controller;

import com.springproject.mu.dto.GeneralBoardDto;
import com.springproject.mu.dto.MemberDto;
import com.springproject.mu.model.GeneralBoard;
import com.springproject.mu.service.GeneralBoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@AllArgsConstructor
public class BoardController {

    GeneralBoardService generalBoardService;

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
