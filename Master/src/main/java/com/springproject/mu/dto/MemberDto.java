package com.springproject.mu.dto;

import com.springproject.mu.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Long id;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 2, max = 20, message = "2 ~ 20 자 사이로 입력 해주세요")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 2, max = 20, message = "2 ~ 20 자 사이로 입력 해주세요")
    private String password;

    private String pwCheck;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식을 맞춰 주세요.")
    private String email;

    @NotBlank(message = "핸드폰 번호는 필수 입력 값입니다.")
    private String phone;

    private Boolean enable;



    public Member toEntity() {
        return Member.builder()
                .id(id)
                .username(username)
                .password(password)
                .email(email)
                .phone(phone)
                .enable(enable)
                .build();
    }


}
