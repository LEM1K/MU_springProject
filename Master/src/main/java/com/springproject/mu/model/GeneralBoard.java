package com.springproject.mu.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "generalboard")
@NoArgsConstructor
public class GeneralBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    //@Column(nullable = false, length = 20)
    private String writer;

    //@Column(nullable = false, length = 8)
    private String category;

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime modifiedTime;

    @Builder
    public GeneralBoard(Long id, String title, String content, String writer, String category,
                        LocalDateTime createTime, LocalDateTime modifiedTime) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.category = category;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }
}
