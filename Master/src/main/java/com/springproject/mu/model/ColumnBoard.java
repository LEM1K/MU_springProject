package com.springproject.mu.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "columnboard")
@NoArgsConstructor
public class ColumnBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false, length = 8)
    private String category;

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime modifiedTime;

    @ManyToOne
    @JoinColumn(name = "username")
    private Member member;


    @Builder
    public ColumnBoard(Long id, String title, String content, String category,
                        LocalDateTime createTime, LocalDateTime modifiedTime, Member member) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.member = member;
    }
}
