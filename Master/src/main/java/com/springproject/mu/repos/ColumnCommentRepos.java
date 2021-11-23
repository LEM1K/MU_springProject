package com.springproject.mu.repos;

import com.springproject.mu.model.ColumnBoard;
import com.springproject.mu.model.ColumnComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColumnCommentRepos extends JpaRepository<ColumnComment, Long> {
    Page<ColumnComment> findAllByColumnBoard(ColumnBoard columnBoard, Pageable pageable);
}
