package com.lpchat.springboot.chatSbApp.service;

import com.lpchat.springboot.chatSbApp.model.ChatModel;
import com.lpchat.springboot.chatSbApp.model.UserAccount;
import com.lpchat.springboot.chatSbApp.repository.ChatRepository;
import com.lpchat.springboot.chatSbApp.repository.UserAccountRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
	
    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public ChatModel getChatById(Long chat_id) {
        return chatRepository.findById(chat_id).orElse(null);
    }
    
	/*
	 * public List<ChatModel> getChatsByUserId(Long userId) { List<ChatModel> chats
	 * = chatRepository.findAll(); return chats.stream() .filter(chat ->
	 * Arrays.asList(chat.getUser_ids()).contains(userId))
	 * .collect(Collectors.toList()); }
	 */
    
	/*
	 * public List<Long> getChatsByUserId(Long userId) { return
	 * chatRepository.findByUserId(userId); }
	 */
}