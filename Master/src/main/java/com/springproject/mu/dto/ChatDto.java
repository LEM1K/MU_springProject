package com.springproject.mu.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;


public class ChatDto {

    private static ChatDto instance = null;

    public static ChatDto Singleton() {
        if(instance == null) {
            instance = new ChatDto();
        }
        return instance;
    }

    public int sessionSize;
}
