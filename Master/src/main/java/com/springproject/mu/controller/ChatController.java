package com.springproject.mu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
public class ChatController {

    @GetMapping("/chat")
    public String index() {

        log.info("@ChatController, chat GET()");

        return "/board/chat";
    }


}
