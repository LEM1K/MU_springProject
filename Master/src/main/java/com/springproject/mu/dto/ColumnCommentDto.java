package com.springproject.mu.dto;

import com.springproject.mu.model.ColumnBoard;
import com.springproject.mu.model.ColumnComment;
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
public class ColumnCommentDto {

    private Long id;

    @NotBlank(message = "내용을 채워 주세요.")
    private String comment;

    private String writer;

    private LocalDateTime commentCreateTime;

    private ColumnBoard columnBoard;

    public ColumnComment toEntity() {
        ColumnComment columnComment = ColumnComment.builder()
                .id(id)
                .comment(comment)
                .commentCreateTime(commentCreateTime)
                .writer(writer)
                .columnBoard(columnBoard)
                .build();

        return columnComment;
    }
}
