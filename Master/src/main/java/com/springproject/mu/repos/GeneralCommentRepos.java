package com.springproject.mu.repos;

import com.springproject.mu.model.GeneralBoard;
import com.springproject.mu.model.GeneralComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralCommentRepos extends JpaRepository<GeneralComment, Long> {
    Page<GeneralComment> findAllByGeneralBoard(GeneralBoard generalBoard, Pageable pageable);
}
