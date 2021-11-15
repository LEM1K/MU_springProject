package com.springproject.mu.dto;

import com.springproject.mu.model.GeneralBoard;
import com.springproject.mu.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralBoardDto {

    private Long id;

    @NotBlank(message = "제목을 채워 주세요.")
    @Size(min = 2, max = 20, message = "2 ~ 20 자 사이로 입력 해주세요")
    private String title;

    @NotBlank(message = "내용을 채워 주세요.")
    @Size(max = 1000, message = "1000 자 이내로 입력 해주세요")
    private String content;

    private String category;

    private LocalDateTime createTime;

    private LocalDateTime modifiedTime;

    private Member member;


    public GeneralBoard toEntity() {
        GeneralBoard generalBoard = GeneralBoard.builder()
                .id(id)
                .title(title)
                .content(content)
                .category(category)
                .createTime(createTime)
                .modifiedTime(modifiedTime)
                .member(member)
                .build();

        return generalBoard;
    }

}
