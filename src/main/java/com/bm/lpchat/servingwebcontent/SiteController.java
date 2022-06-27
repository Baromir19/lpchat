package com.bm.lpchat.servingwebcontent;

import com.bm.lpchat.model.User;
import com.bm.lpchat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class SiteController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("*")
    public String index() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User userData, HttpSession httpSession){
        try {
        System.out.println(userData);
        userRepository.save(userData);
        httpSession.setAttribute("message", "User register successfully...");
        } catch (Exception exception) {
            System.out.println(exception);
            httpSession.setAttribute("message", exception);
        };
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
