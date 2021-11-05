package com.springproject.mu.service;

import com.springproject.mu.dto.MemberDto;
import com.springproject.mu.model.Member;
import com.springproject.mu.model.Role;
import com.springproject.mu.repos.MemberRepos;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepos memberRepos;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Member saveMember(MemberDto memberDto) {

        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());

        memberDto.setPassword(encodedPassword);
        memberDto.setEnable(true);

        Member member = memberDto.toEntity();

        Role role = new Role();
        role.setId(1L);
        member.getRoles().add(role);
        return memberRepos.save(member);
    }

    //아이디 중복 확인
    public int idCheck(String username) {
        Optional<Member> member = memberRepos.findByUsername(username);

        if(member.isPresent()) {
            return 0;
        }
        else {
            return 1;
        }
    }

    //필드 유효성 검사
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }


}
