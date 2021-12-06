package com.springproject.mu.controller;

import com.springproject.mu.dto.ColumnBoardDto;
import com.springproject.mu.dto.ColumnCommentDto;
import com.springproject.mu.dto.GeneralBoardDto;
import com.springproject.mu.dto.GeneralCommentDto;
import com.springproject.mu.model.ColumnBoard;
import com.springproject.mu.model.ColumnComment;
import com.springproject.mu.model.GeneralBoard;
import com.springproject.mu.model.GeneralComment;
import com.springproject.mu.repos.ColumnBoardRepos;
import com.springproject.mu.repos.ColumnCommentRepos;
import com.springproject.mu.repos.GeneralBoardRepos;
import com.springproject.mu.repos.GeneralCommentRepos;
import com.springproject.mu.service.ColumnBoardService;
import com.springproject.mu.service.GeneralBoardService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BoardController {

    GeneralBoardService generalBoardService;

    GeneralBoardRepos generalBoardRepos;

    GeneralCommentRepos generalCommentRepos;

    ColumnBoardService columnBoardService;

    ColumnBoardRepos columnBoardRepos;

    ColumnCommentRepos columnCommentRepos;

    @GetMapping("/board/general")
    public String printGeneralPage(Model model, @PageableDefault(size = 10) Pageable pageable,
                                   @RequestParam(required = false, defaultValue = "") String searchText) {

        //Page<GeneralBoard> generalBoards = generalBoardRepos.findAll(pageable);
        Page<GeneralBoard> generalBoards = generalBoardRepos.findByTitleContaining(searchText, pageable);
        int startpage = 1;
        int endpage = generalBoards.getTotalPages();

        model.addAttribute("startpage", startpage);
        model.addAttribute("endpage", endpage);

        model.addAttribute("generalBoards", generalBoards);
        return "board/general";
    }

    @GetMapping("/board/generaldetail")
    public String generalPostDetail(@RequestParam Long id,
                                    @PageableDefault(size = 8) Pageable pageable, Model model1, Model model2) {

        Optional<GeneralBoard> generalBoard = generalBoardRepos.findById(id);

        Page<GeneralComment> generalComment = generalCommentRepos.findAllByGeneralBoard(generalBoard.get(), pageable);

        int startpage = 1;
        int endpage = generalComment.getTotalPages();

        model1.addAttribute("generalBoard", generalBoard.get());
        model2.addAttribute("generalComment", generalComment);
        model2.addAttribute("startpage", startpage);
        model2.addAttribute("endpage", endpage);

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

    @PostMapping("/board/generaldetail/comment")
    public String insertGeneralComment(@RequestParam String id, @Valid GeneralCommentDto generalCommentDto,
                                       Errors errors, Model model, Authentication authentication) {

        generalCommentDto.setId(null);


        model.addAttribute("columnCommentDto", generalCommentDto);
        String username = authentication.getName();

        if(errors.hasErrors()) {

            Map<String, String> validatorResult = generalBoardService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "redirect:/board/generaldetail?id=" + Long.parseLong(id);
        }

        generalBoardService.insertComment(id, generalCommentDto, username);

        return "redirect:/board/generaldetail?id=" + Long.parseLong(id);
    }


    @GetMapping("/board/column")
    public String printColumnPage(Model model, @PageableDefault(size = 10) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {

        //Page<GeneralBoard> generalBoards = generalBoardRepos.findAll(pageable);
        Page<ColumnBoard> columnBoards = columnBoardRepos.findByTitleContaining(searchText, pageable);
        int startpage = 1;
        int endpage = columnBoards.getTotalPages();

        model.addAttribute("startpage", startpage);
        model.addAttribute("endpage", endpage);

        model.addAttribute("columnBoards", columnBoards);
        return "board/column";
    }

    @GetMapping("/board/columndetail")
    public String columnPostDetail(@RequestParam Long id, @PageableDefault(size = 8) Pageable pageable, Model model1, Model model2) {

        Optional<ColumnBoard> columnBoards = columnBoardRepos.findById(id);

        Page<ColumnComment> columnComment = columnCommentRepos.findAllByColumnBoard(columnBoards.get(), pageable);

        int startpage = 1;
        int endpage = columnComment.getTotalPages();

        model1.addAttribute("columnBoard", columnBoards.get());
        model2.addAttribute("columnComment", columnComment);
        model2.addAttribute("startpage", startpage);
        model2.addAttribute("endpage", endpage);

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


    @PostMapping("/board/columndetail/comment")
    public String insertColumnComment(@Valid ColumnCommentDto columnCommentDto, Errors errors, @RequestParam String id, Model model, Authentication authentication) {

        columnCommentDto.setId(null);

        String username = authentication.getName();
        model.addAttribute("columnCommentDto", columnCommentDto);

        if(errors.hasErrors()) {

            Map<String, String> validatorResult = columnBoardService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "redirect:/board/columndetail?id=" + Long.parseLong(id);
        }

        columnBoardService.insertComment(id, columnCommentDto, username);

        return "redirect:/board/columndetail?id=" + Long.parseLong(id);
    }



}
