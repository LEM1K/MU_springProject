package com.springproject.mu.controller;

import com.springproject.mu.dto.MemberDto;
import com.springproject.mu.model.Member;
import com.springproject.mu.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MemberController {

    MemberService memberService;

    MemberAPIController memberAPIController;

    @PostMapping("/member/signup")
    public String registerExec(Member member) {
        int check = memberAPIController.idCheck(member.getUsername());

        System.out.println(member.getPassword());
        System.out.println(member.getRoles());
        System.out.println(member.getUsername());
        if(check == 1) {
            memberService.saveMember(member);
            return "redirect:/member/login";
        }
        else {
            return "/member/signup";
        }
    }

}
