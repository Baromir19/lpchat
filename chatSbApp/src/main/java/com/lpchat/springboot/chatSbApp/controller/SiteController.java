package com.lpchat.springboot.chatSbApp.controller;

import com.lpchat.springboot.chatSbApp.configuration.JwtTokenProvider;
import com.lpchat.springboot.chatSbApp.model.ChatMessage;
import com.lpchat.springboot.chatSbApp.model.ChatModel;
import com.lpchat.springboot.chatSbApp.model.UserAccount;
import com.lpchat.springboot.chatSbApp.service.ChatMessageService;
import com.lpchat.springboot.chatSbApp.service.ChatService;
import com.lpchat.springboot.chatSbApp.service.UserAccountService;

import java.sql.Timestamp;
import java.util.List;

import com.lpchat.springboot.chatSbApp.configuration.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SiteController {

    private final UserAccountService userAccountService;
    private final ChatService chatService;
    private final ChatMessageService chatMessageService;
	
    @Autowired
    public SiteController(UserAccountService userAccountService, ChatService chatService, ChatMessageService chatMessageService) {
        this.userAccountService = userAccountService;
        this.chatService = chatService;
        this.chatMessageService = chatMessageService;
    }
    
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
    
    @GetMapping("/chats")
    public String showUserChats(HttpServletRequest request, Model model) {
        JwtTokenProvider TokenProvider = new JwtTokenProvider();

        String token = TokenProvider.getTokenFromSession(request);
        
        if (!TokenProvider.validateToken(token)) {
            return "redirect:/login";
        }
    	
        String phone = TokenProvider.getPhoneFromToken(token);
        UserAccount currentUser = userAccountService.getUserAccountByPhone(phone);
        
//        List<ChatModel> chat = chatService.getChatsByUserId(currentUser.getId());
//        model.addAttribute("chat", chat);
        
//        List<Long> chats = chatService.getChatsByUserId(currentUser.getId());
//        chats.stream().forEach(System.out::println);
//        model.addAttribute("chats", chats);
        
        model.addAttribute("lastname", currentUser.getLastname());
        model.addAttribute("firstname", currentUser.getFirstname());
        model.addAttribute("middlename", currentUser.getMiddlename());
        model.addAttribute("currentUserId", currentUser.getId());        
        //List<ChatMessage> messages = chatMessageService.getAllMessages();
        //model.addAttribute("messages", messages);
        model.addAttribute("message", new ChatMessage());
        
    	return "chats";
    }
    
    @MessageMapping("/chats/message")
    @SendTo("/chats/message")
    public ChatMessage handleMessage(ChatMessage message) { //, Model model
    	//UserAccount currentUser = (UserAccount) model.asMap().get("currentUserId");
    	
        chatMessageService.addMessage(message, 23L); 
    	/*
        String encryptedMessage = AESUtil.encryptMessage(message.getMessage_text());
        if (encryptedMessage != null) {
            message.setMessage_text(encryptedMessage);
            chatMessageService.addMessage(message, 23L);
        }
        */
        return message;
    }
    
    @SubscribeMapping("/chats/message")
    public String sendOneTimeMessage() {
    	System.out.println("subscribe map");
        return "server one-time message via the application";
    }
/*    public List<ChatMessage> getAllMessages() {
    	System.out.println("all messages sended");
    	
        return chatMessageService.getAllMessages();
    }*/
    
    /*
    @PostMapping("/sendMessage")
    public ResponseEntity<String> createMessage(@ModelAttribute ChatMessage message, Model model) {
        Long currentUserId = (Long) model.getAttribute("currentUserId");
        chatMessageService.addMessage(message, currentUserId); 
        
        //System.out.println((Long) model.getAttribute("currentUserId"));
     
        return ResponseEntity.ok("Message created");
    }
    */
    
    /*
    @GetMapping("/chats/{chat_id}")
    public String showChatPage(@PathVariable("chat_id") Long chat_id, Model model) {
        ChatModel chat = chatService.getChatById(chat_id);

        model.addAttribute("chat", chat);

        return "chats";
    }
	*/
}
