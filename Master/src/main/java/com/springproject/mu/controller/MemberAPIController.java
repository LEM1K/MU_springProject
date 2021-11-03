package com.springproject.mu.controller;

import com.springproject.mu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MemberAPIController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(method = RequestMethod.GET, value = "/idCheck/{username}")
    public int idCheck(@PathVariable String username) {

        int count = 0;
        count = memberService.idCheck(username);

        return count;
    }
}
