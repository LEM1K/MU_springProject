package com.springproject.mu.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Data
@Entity
@NoArgsConstructor
public class GeneralComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false, length = 20)
    private String writer;

    @CreatedDate
    private LocalDateTime commentCreateTime;

    @ManyToOne
    @JoinColumn(name = "title")
    private GeneralBoard generalBoard;

    @Builder
    public GeneralComment(Long id, String comment, String writer, LocalDateTime commentCreateTime, GeneralBoard generalBoard) {
        this.id = id;
        this.comment = comment;
        this.writer = writer;
        this.commentCreateTime = commentCreateTime;
        this.generalBoard = generalBoard;
    }
}
