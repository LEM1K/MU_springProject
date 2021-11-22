package com.springproject.mu.controller;

import com.springproject.mu.dto.ColumnBoardDto;
import com.springproject.mu.dto.GeneralBoardDto;
import com.springproject.mu.dto.MemberDto;
import com.springproject.mu.model.ColumnBoard;
import com.springproject.mu.model.GeneralBoard;
import com.springproject.mu.repos.ColumnBoardRepos;
import com.springproject.mu.repos.GeneralBoardRepos;
import com.springproject.mu.service.ColumnBoardService;
import com.springproject.mu.service.GeneralBoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BoardController {

    GeneralBoardService generalBoardService;

    GeneralBoardRepos generalBoardRepos;

    ColumnBoardService columnBoardService;

    ColumnBoardRepos columnBoardRepos;

    @GetMapping("/board/general")
    public String printGeneralPage(Model model, @PageableDefault(size = 10) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {

        //Page<GeneralBoard> generalBoards = generalBoardRepos.findAll(pageable);
        Page<GeneralBoard> generalBoards = generalBoardRepos.findByTitleContaining(searchText, pageable);
        int startpage = Math.max(1, generalBoards.getPageable().getPageNumber() - 4);
        int endpage = Math.min(generalBoards.getTotalPages(), generalBoards.getPageable().getPageNumber() + 4);

        model.addAttribute("startpage", startpage);
        model.addAttribute("endpage", endpage);

        model.addAttribute("generalBoards", generalBoards);
        return "board/general";
    }

    @GetMapping("/board/generaldetail")
    public String generalPostDetail(@RequestParam Long id, Model model) {

       Optional<GeneralBoard> generalBoard = generalBoardRepos.findById(id);

       model.addAttribute("generalBoard", generalBoard.get());

        return "board/generaldetail";
    }

    @GetMapping("/board/generalform")
    public String generalFormPrint() {
        return "board/generalform";
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
            return "board/generalform";
        }


        generalBoardService.insertPost(generalBoardDto, username);

        return "redirect:/board/general";
    }

    @GetMapping("/board/generaluform")
    public String generalUpdateFormPrint(@RequestParam String id, Model model) {

        Optional<GeneralBoard> generalBoard = generalBoardRepos.findById(Long.parseLong(id));
        GeneralBoardDto generalBoardDto = new GeneralBoardDto(generalBoard.get().getId(),
                generalBoard.get().getTitle(), generalBoard.get().getContent(),
                generalBoard.get().getCategory(), generalBoard.get().getCreateTime(),
                generalBoard.get().getModifiedTime(), generalBoard.get().getMember());

        model.addAttribute("generalBoardDto", generalBoardDto);

        return "board/generaluform";
    }


    @PostMapping ("/board/generaluform")
    public String generalFormUpdate(@Valid GeneralBoardDto generalBoardDto, Errors errors, Model model, Authentication authentication) {

        String username = authentication.getName();
        //model.addAttribute("generalBoardDto", generalBoardDto);

        if(errors.hasErrors()) {

            Map<String, String> validatorResult = generalBoardService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "board/generaluform";
        }

        generalBoardService.updatePost(generalBoardDto, username);

        return "redirect:/board/general";
    }


    @GetMapping("/board/column")
    public String printColumnPage(Model model, @PageableDefault(size = 10) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {

        //Page<GeneralBoard> generalBoards = generalBoardRepos.findAll(pageable);
        Page<ColumnBoard> columnBoards = columnBoardRepos.findByTitleContaining(searchText, pageable);
        int startpage = Math.max(1, columnBoards.getPageable().getPageNumber() - 4);
        int endpage = Math.min(columnBoards.getTotalPages(), columnBoards.getPageable().getPageNumber() + 4);

        model.addAttribute("startpage", startpage);
        model.addAttribute("endpage", endpage);

        model.addAttribute("columnBoards", columnBoards);
        return "board/column";
    }

    @GetMapping("/board/columndetail")
    public String columnPostDetail(@RequestParam Long id, Model model) {

        Optional<ColumnBoard> columnBoards = columnBoardRepos.findById(id);

        model.addAttribute("columnBoard", columnBoards.get());

        return "board/columndetail";
    }

    @GetMapping("/board/columnform")
    public String columnFormPrint() {
        return "board/columnform";
    }


    @PostMapping("/board/columnform")
    public String columnFormInsert(@Valid ColumnBoardDto columnBoardDto, Errors errors, Model model, Authentication authentication) {

        String username = authentication.getName();
        model.addAttribute("columnBoardDto", columnBoardDto);

        System.out.println(columnBoardDto.getContent());

        if(errors.hasErrors()) {

            Map<String, String> validatorResult = columnBoardService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "board/columnform";
        }


        columnBoardService.insertPost(columnBoardDto, username);

        return "redirect:/board/column";
    }

    @GetMapping("/board/columnuform")
    public String columnUpdateFormPrint(@RequestParam String id, Model model) {

        Optional<ColumnBoard> columnBoard = columnBoardRepos.findById(Long.parseLong(id));
        ColumnBoardDto columnBoardDto = new ColumnBoardDto(columnBoard.get().getId(),
                columnBoard.get().getTitle(), columnBoard.get().getContent(),
                columnBoard.get().getCategory(), columnBoard.get().getCreateTime(),
                columnBoard.get().getModifiedTime(), columnBoard.get().getMember());

        model.addAttribute("columnBoardDto", columnBoardDto);

        return "board/columnuform";
    }


    @PostMapping ("/board/columnuform")
    public String columnFormUpdate(@Valid ColumnBoardDto columnBoardDto, Errors errors, Model model, Authentication authentication) {

        String username = authentication.getName();

        if(errors.hasErrors()) {

            Map<String, String> validatorResult = columnBoardService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "board/columnuform";
        }

        columnBoardService.updatePost(columnBoardDto, username);

        return "redirect:/board/column";
    }


}
