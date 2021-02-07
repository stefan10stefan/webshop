package com.controller;

import com.model.Message;
import com.model.dto.ShopDTO;
import com.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping(path="/")
    public List<Message> getAll() {
        return messageService.getMessages();
    }

}
