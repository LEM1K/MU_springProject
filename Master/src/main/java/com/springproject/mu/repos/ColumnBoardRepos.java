package com.springproject.mu.repos;

import com.springproject.mu.model.ColumnBoard;
import com.springproject.mu.model.GeneralBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnBoardRepos extends JpaRepository<ColumnBoard, Long> {

    Page<ColumnBoard> findByTitleContaining(String title, Pageable pageable);

}