package com.springproject.mu.controller;

import com.springproject.mu.repos.MemberRepos;
import com.springproject.mu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class MemberAPIController {

    @Autowired
    private MemberRepos memberRepos;

    @Autowired
    private MemberService memberService;

    @RequestMapping(method = RequestMethod.GET, value = "/idCheck/{username}")
    public int idCheck(@PathVariable String username) {

        int count = 0;
        count = memberService.idCheck(username);

        return count;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/member/delete/{id}")
    public void memberDelete(@PathVariable String id) {

        Long thisId = Long.parseLong(id);
        memberRepos.deleteById(thisId);
    }


}
