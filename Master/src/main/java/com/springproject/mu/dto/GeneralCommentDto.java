package com.springproject.mu.dto;


import com.springproject.mu.model.ColumnBoard;
import com.springproject.mu.model.ColumnComment;
import com.springproject.mu.model.GeneralBoard;
import com.springproject.mu.model.GeneralComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralCommentDto {

    private Long id;

    @NotBlank(message = "내용을 채워 주세요.")
    private String comment;

    private String writer;

    private LocalDateTime commentCreateTime;

    private GeneralBoard generalBoard;

    public GeneralComment toEntity() {
        GeneralComment generalComment = GeneralComment.builder()
                .id(id)
                .comment(comment)
                .writer(writer)
                .commentCreateTime(commentCreateTime)
                .generalBoard(generalBoard)
                .build();

        return generalComment;
    }
}

