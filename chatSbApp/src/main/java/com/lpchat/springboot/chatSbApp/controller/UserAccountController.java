package com.lpchat.springboot.chatSbApp.controller;

import com.lpchat.springboot.chatSbApp.model.UserAccount;
import com.lpchat.springboot.chatSbApp.service.UserAccountService;
import com.lpchat.springboot.chatSbApp.configuration.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserAccountController {

    private final UserAccountService userAccountService;
    
    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    JwtTokenProvider TokenProvider = new JwtTokenProvider();
    
    /// Registration
    
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserAccount userAccount, Model model) {
        if (userAccountService.isPhoneExists(userAccount.getPhone())) {
        	model.addAttribute("error", "Телефон вже зайнятий");
            return "register";
        } else if (userAccountService.isUsernameExists(userAccount.getUsername())) {
        	model.addAttribute("error", "Нік вже зайнятий");
            return "register";
        }
        
        userAccountService.saveUserAccount(userAccount);
        return "redirect:/login";
    }
    
    /// Authorisation
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
    @PostMapping("/login")
    public String processLoginForm(@RequestParam("phone") String phone,
            					   @RequestParam("password") String password,
            					   	Model model) {
        if (userAccountService.authenticate(phone, password)) {
            // Generating token
            String token = TokenProvider.generateToken(phone);
            
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
            
            // Saving token
            TokenProvider.saveTokenInClient(token, response);
            
            return "redirect:/chats";
        } else {
            model.addAttribute("error", "Не вірний номер чи пароль");
            return "login";
        }
    }
    
    /// Logout
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
      Cookie cookie = new Cookie("token", null);
      cookie.setPath("/");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
      
      return "redirect:/login";
    }
    
    /// Token functions
    /*
    private String generateToken(String phone) {
    	SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        
		String token = Jwts.builder()
                .setSubject(phone)
                .signWith(secretKey, SignatureAlgorithm.HS256) // deprecated!
                .compact();
        return token;
    }

    private void saveTokenInClient(String token, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", token);
        
        // Cookie settings
        cookie.setPath("/"); 
        cookie.setMaxAge(7 * 24 * 60 * 60);
        
        // Cookie to response
        response.addCookie(cookie);
    }*/
    
}