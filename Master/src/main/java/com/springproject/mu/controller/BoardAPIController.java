package com.springproject.mu.controller;

import com.springproject.mu.repos.GeneralBoardRepos;
import com.springproject.mu.service.GeneralBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardAPIController {

    @Autowired
    private GeneralBoardService generalBoardService;

    @Autowired
    private GeneralBoardRepos generalBoardRepos;

    @DeleteMapping("/board/generaldelete")
    public int generalDelete(@RequestParam("postId") String postId, @RequestParam("postwriter") String postwriter, Authentication authentication) {

        int count = 0;
        String username = authentication.getName();

        count = generalBoardService.writerCheck(username, postwriter);

        if(count == 1) {
            generalBoardRepos.deleteById(Long.parseLong(postId));
        }

        return count;
    }

    @GetMapping("/board/generalupdate")
    public int generalUpdate(@RequestParam("postId") String postId, @RequestParam("postwriter") String postwriter, Authentication authentication) {
        int count = 0;
        String username = authentication.getName();

        count = generalBoardService.writerCheck(username, postwriter);

        return count;
    }
}
