package com.lpchat.springboot.chatSbApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SbErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Вернуть представление ошибки или перенаправить на другую страницу
        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}