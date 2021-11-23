package com.springproject.mu.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Data
@Entity
@NoArgsConstructor
public class ColumnComment {

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
    private ColumnBoard columnBoard;

    @Builder
    public ColumnComment(Long id, String comment, String writer, LocalDateTime commentCreateTime, ColumnBoard columnBoard) {
        this.id = id;
        this.comment = comment;
        this.writer = writer;
        this.commentCreateTime = commentCreateTime;
        this.columnBoard = columnBoard;
    }
}
