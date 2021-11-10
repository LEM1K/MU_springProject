package com.springproject.mu.repos;

import com.springproject.mu.model.GeneralBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralBoardRepos extends JpaRepository<GeneralBoard, Long> {
}
