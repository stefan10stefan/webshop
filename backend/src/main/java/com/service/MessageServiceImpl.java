package com.service;

import com.model.Message;
import com.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }
}
