package com.springproject.mu.controller;

import com.springproject.mu.dto.MemberDto;
import com.springproject.mu.model.Member;
import com.springproject.mu.repos.MemberRepos;
import com.springproject.mu.service.MemberService;
import lombok.AllArgsConstructor;
import org.apache.catalina.SessionEvent;
import org.apache.catalina.SessionListener;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class MemberController {

    MemberService memberService;

    MemberRepos memberRepos;

    MemberAPIController memberAPIController;

    @PostMapping("/member/signup")
    public String registerExec(@Valid MemberDto memberDto, Errors errors, Model model) {

        model.addAttribute("memberDto", memberDto);

        int idCheck = memberAPIController.idCheck(memberDto.getUsername());
        int pwCheck = memberService.pwCheck(memberDto.getPassword(), memberDto.getPwCheck());
        if(errors.hasErrors() || idCheck == 0 || pwCheck == 0) {

            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "member/signup";
        }

        memberService.saveMember(memberDto);
        return "redirect:/member/login";

    }

    @GetMapping("/member/info")
    public String memberInfo(Authentication authentication, Model model) {

        String username = authentication.getName();

        Optional<Member> member = memberRepos.findByUsername(username);
        Member memberinfo = member.get();

        model.addAttribute("memberinfo", memberinfo);


        return "member/info";
    }

    @GetMapping("/admin/memberManager")
    public String getAdminPage(Model model) {

        List<Member> memberList = memberRepos.findAll();

        model.addAttribute("members", memberList);

        return "admin/memberManager";
    }

    @GetMapping("/admin/forbidden")
    public String getForbiddenPage() {
        return "admin/forbidden";
    }




}
