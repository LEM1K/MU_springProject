package com.springproject.mu.controller;

import com.springproject.mu.dto.MemberDto;
import com.springproject.mu.model.Member;
import com.springproject.mu.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MemberController {

    MemberService memberService;

    MemberAPIController memberAPIController;

    @PostMapping("/member/signup")
    public String registerExec(@Valid MemberDto memberDto, Errors errors, Model model) {

        model.addAttribute("memberDto", memberDto);


        System.out.println(memberDto.getUsername());
        System.out.println(memberDto.getPassword());
        System.out.println(memberDto.getPwCheck());
        System.out.println(memberDto.getEmail());
        System.out.println(memberDto.getPhone());


        int idCheck = memberAPIController.idCheck(memberDto.getUsername());
        int pwCheck = memberService.pwCheck(memberDto.getPassword(), memberDto.getPwCheck());
        if(errors.hasErrors() || idCheck == 0 || pwCheck == 0) {

            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "/member/signup";
        }

        memberService.saveMember(memberDto);
        return "redirect:/member/login";

    }


}
