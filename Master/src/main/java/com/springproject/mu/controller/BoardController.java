package com.springproject.mu.controller;

import com.springproject.mu.dto.GeneralBoardDto;
import com.springproject.mu.dto.MemberDto;
import com.springproject.mu.model.GeneralBoard;
import com.springproject.mu.service.GeneralBoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class BoardController {

    GeneralBoardService generalBoardService;


    @GetMapping("/board/generalform")
    public String generalFormPrint() {
        return "/board/generalform";
    }

    @PostMapping("/board/generalform")
    public void generalFormInsert(GeneralBoardDto generalBoardDto) {


        System.out.println("id : " + generalBoardDto.getId());
        System.out.println("title : " + generalBoardDto.getTitle());
        System.out.println("content : " + generalBoardDto.getContent());
        System.out.println("writer : " + generalBoardDto.getWriter());

        generalBoardService.insertPost(generalBoardDto);


    }
}
