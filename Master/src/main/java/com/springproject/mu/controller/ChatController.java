package com.springproject.mu.controller;

import com.springproject.mu.dto.ChatDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
public class ChatController {


    @GetMapping("/chat")
    public String index(Model model) {

        //System.out.println(ChatDto.Singleton().sessionSize);
        int sessionSize = ChatDto.Singleton().sessionSize;
        model.addAttribute("sessionSize", sessionSize);
        log.info("@ChatController, chat GET()");

        return "board/chat";
    }
}
