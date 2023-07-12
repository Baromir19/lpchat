package com.lpchat.springboot.chatSbApp.service;

import com.lpchat.springboot.chatSbApp.model.ChatMessage;
import com.lpchat.springboot.chatSbApp.model.UserAccount;
import com.lpchat.springboot.chatSbApp.repository.ChatMessageRepository;
import com.lpchat.springboot.chatSbApp.repository.UserAccountRepository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {
	
    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }
    
    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll();
    }
    
    public void addMessage(ChatMessage message) {
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        chatMessageRepository.save(message);
    }

}