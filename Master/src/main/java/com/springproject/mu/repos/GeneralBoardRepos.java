package com.springproject.mu.repos;

import com.springproject.mu.model.GeneralBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneralBoardRepos extends JpaRepository<GeneralBoard, Long> {

    Page<GeneralBoard> findByTitleContaining(String title, Pageable pageable);
    List<GeneralBoard> findTop10ByOrderByModifiedTimeDesc();
}
