package com.lpchat.springboot.chatSbApp.controller;

import com.lpchat.springboot.chatSbApp.configuration.JwtTokenProvider;
import com.lpchat.springboot.chatSbApp.model.UserAccount;
import com.lpchat.springboot.chatSbApp.service.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SiteController {

    private final UserAccountService userAccountService;
	
    @Autowired
    public SiteController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
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
        
        model.addAttribute("lastname", currentUser.getLastname());
        model.addAttribute("firstname", currentUser.getFirstname());
        model.addAttribute("middlename", currentUser.getMiddlename());
        
    	return "chats";
    }
    
    @GetMapping("/chats/{id}")
    public String showChatPage(@PathVariable("id") Long userId, Model model) {
        model.addAttribute("userId", userId);

        return "chat";
    }
	
}
