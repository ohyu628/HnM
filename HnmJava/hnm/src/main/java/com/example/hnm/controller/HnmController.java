package com.example.hnm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.hnm.model.dto.HnmDto;
import com.example.hnm.service.HnmService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HnmController {
    
    @GetMapping("/hello")
    public String gethello(){
        return "hello world";
    }

    @Autowired
    private HnmService hnmService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute HnmDto dto, HttpServletRequest request){
        HnmDto isOk = hnmService.loginCustomer(dto);
        if(isOk == null){
            return "login";
        }
        return "home";
    }

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }
    
}