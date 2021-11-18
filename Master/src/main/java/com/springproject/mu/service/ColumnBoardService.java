package com.springproject.mu.service;

import com.springproject.mu.dto.ColumnBoardDto;
import com.springproject.mu.dto.GeneralBoardDto;
import com.springproject.mu.model.ColumnBoard;
import com.springproject.mu.model.GeneralBoard;
import com.springproject.mu.model.Member;
import com.springproject.mu.repos.ColumnBoardRepos;
import com.springproject.mu.repos.GeneralBoardRepos;
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
public class ColumnBoardService {

    @Autowired
    private ColumnBoardRepos columnBoardRepos;

    @Autowired
    private MemberRepos memberRepos;


    public void insertPost(ColumnBoardDto columnBoardDto, String username) {

        Optional<Member> member = memberRepos.findByUsername(username);

        ColumnBoard columnBoard = columnBoardDto.toEntity();

        columnBoard.setMember(member.get());
        columnBoard.setCreateTime(LocalDateTime.now());
        columnBoard.setModifiedTime(LocalDateTime.now());
        columnBoard.setCategory("column");

        columnBoardRepos.save(columnBoard);

    }

    public void updatePost(ColumnBoardDto columnBoardDto, String username) {
        Optional<ColumnBoard> columnBoardOptional = columnBoardRepos.findById(columnBoardDto.getId());

        LocalDateTime createTime = columnBoardOptional.get().getCreateTime();
        Optional<Member> member = memberRepos.findByUsername(username);

        ColumnBoard columnBoard = columnBoardDto.toEntity();

        columnBoard.setMember(member.get());
        columnBoard.setCreateTime(createTime);
        columnBoard.setModifiedTime(LocalDateTime.now());
        columnBoard.setCategory("general");

        columnBoardRepos.save(columnBoard);
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
