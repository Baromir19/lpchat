package com.lpchat.springboot.chatSbApp.controller;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.lpchat.springboot.chatSbApp.model.ChatMessage;
import com.lpchat.springboot.chatSbApp.model.UserAccount;
import com.lpchat.springboot.chatSbApp.service.ChatMessageService;
import com.lpchat.springboot.chatSbApp.service.ChatService;
import com.lpchat.springboot.chatSbApp.service.UserAccountService;

@Controller*/
public class WebSocketController {
/*
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
	
    private final ChatMessageService chatMessageService;
	
    @Autowired
    public WebSocketController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }
	
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String handleHelloMessage(String message) {
        String response = "Hello, " + message + "!";
        return response;
    }
    
    @MessageMapping("/sendMessage")
    public void sendMessage(ChatMessage message) {
        //String phone = TokenProvider.getPhoneFromToken(token);
        //UserAccount currentUser = userAccountService.getUserAccountByPhone(phone);

        //message.setSenderId(currentUser.getId());
        chatMessageService.addMessage(message, 23L); // 23L - my user Id

        messagingTemplate.convertAndSend("/topic/greetings", message);
    }*/

}