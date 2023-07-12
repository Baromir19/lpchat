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

import org.springframework.beans.factory.annotation.Autowired;
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
        //model.addAttribute("userId", currentUser.getId());
        
        //List<ChatMessage> messages = chatMessageService.getAllMessages();
        //model.addAttribute("messages", messages);
        model.addAttribute("message", new ChatMessage());
        
    	return "chats";
    }
    
    @PostMapping("/chats")
    public String createMessage(@ModelAttribute ChatMessage message) {
    	
        chatMessageService.addMessage(message);
        
        System.out.println("TEST MESSAGE");
        System.out.println(message.getMessage_text());   
        
        return "redirect:/chats";
    }
    
    /*
    @GetMapping("/chats/{chat_id}")
    public String showChatPage(@PathVariable("chat_id") Long chat_id, Model model) {
        ChatModel chat = chatService.getChatById(chat_id);

        model.addAttribute("chat", chat);

        return "chats";
    }
	*/
}
