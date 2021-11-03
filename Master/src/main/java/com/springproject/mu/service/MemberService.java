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

import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepos memberRepos;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Member saveMember(Member member) {

        String encodedPassword = passwordEncoder.encode(member.getPassword());

        member.setPassword(encodedPassword);
        member.setEnable(true);

        Role role = new Role();
        role.setId(2L);
        member.getRoles().add(role);
        return memberRepos.save(member);
    }

    public int idCheck(String username) {
        Optional<Member> member = memberRepos.findByUsername(username);

        if(member.isPresent()) {
            return 0;
        }
        else {
            return 1;
        }
    }


}
