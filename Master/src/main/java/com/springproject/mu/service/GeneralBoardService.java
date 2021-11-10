package com.springproject.mu.service;

import com.springproject.mu.dto.GeneralBoardDto;
import com.springproject.mu.model.GeneralBoard;
import com.springproject.mu.repos.GeneralBoardRepos;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GeneralBoardService {

    @Autowired
    private GeneralBoardRepos generalBoardRepos;

    public void insertPost(GeneralBoardDto generalBoardDto) {
        GeneralBoard generalBoard = generalBoardDto.toEntity();

        generalBoardRepos.save(generalBoard);

    }
}
