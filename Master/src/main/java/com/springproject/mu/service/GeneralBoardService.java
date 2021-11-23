package com.springproject.mu.service;

import com.springproject.mu.dto.ColumnCommentDto;
import com.springproject.mu.dto.GeneralBoardDto;
import com.springproject.mu.dto.GeneralCommentDto;
import com.springproject.mu.model.*;
import com.springproject.mu.repos.GeneralBoardRepos;
import com.springproject.mu.repos.GeneralCommentRepos;
import com.springproject.mu.repos.MemberRepos;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GeneralBoardService {

    @Autowired
    private GeneralBoardRepos generalBoardRepos;

    @Autowired
    private GeneralCommentRepos generalCommentRepos;

    @Autowired
    private MemberRepos memberRepos;


    public void insertPost(GeneralBoardDto generalBoardDto, String username) {

        Optional<Member> member = memberRepos.findByUsername(username);

        GeneralBoard generalBoard = generalBoardDto.toEntity();

        generalBoard.setMember(member.get());
        generalBoard.setCreateTime(LocalDateTime.now());
        generalBoard.setModifiedTime(LocalDateTime.now());
        generalBoard.setCategory("general");

        generalBoardRepos.save(generalBoard);

    }

    public void updatePost(GeneralBoardDto generalBoardDto, String username) {
        Optional<GeneralBoard> generalBoardOptional = generalBoardRepos.findById(generalBoardDto.getId());

        LocalDateTime createTime = generalBoardOptional.get().getCreateTime();
        Optional<Member> member = memberRepos.findByUsername(username);

        GeneralBoard generalBoard = generalBoardDto.toEntity();

        generalBoard.setMember(member.get());
        generalBoard.setCreateTime(createTime);
        generalBoard.setModifiedTime(LocalDateTime.now());
        generalBoard.setCategory("general");

        generalBoardRepos.save(generalBoard);
    }


    public void insertComment(String id, GeneralCommentDto generalCommentDto, String username) {

        GeneralComment generalComment = generalCommentDto.toEntity();

        Optional<GeneralBoard> generalBoard = generalBoardRepos.findById(Long.parseLong(id));
        generalComment.setGeneralBoard(generalBoard.get());
        generalComment.setWriter(username);
        generalComment.setCommentCreateTime(LocalDateTime.now());

        generalCommentRepos.save(generalComment);

    }

    public int writerCheck(String username, String writer) {
        if(username.equals(writer)) {
            return 1;
        }
        else {
            return 0;
        }
    }

    //필드 유효성 검사
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }



}
