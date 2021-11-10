package com.springproject.mu.dto;

import com.springproject.mu.model.GeneralBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralBoardDto {

    private Long id;

    private String title;

    private String content;

    private String writer;

    private String category;

    private LocalDateTime createTime;

    private LocalDateTime modifiedTime;


    public GeneralBoard toEntity() {
        GeneralBoard generalBoard = GeneralBoard.builder()
                .id(id)
                .title(title)
                .content(content)
                .writer(writer)
                .category(category)
                .createTime(createTime)
                .modifiedTime(modifiedTime)
                .build();

        return generalBoard;
    }

}
