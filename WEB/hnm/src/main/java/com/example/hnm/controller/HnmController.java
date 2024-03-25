package com.example.hnm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hnm.dto.CustomerDto;
import com.example.hnm.service.CustomerService;

import org.springframework.ui.Model;

@Controller
public class HnmController {
    
    @Autowired
    private CustomerService customerService;

    // 홈페이지 //
    @GetMapping("/homepage")
    public String homepage(Authentication authentication, Model model) {
        if(authentication != null){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("username", userDetails.getUsername());
        }
        return "homepage";
    }

    // // 로그아웃 기능 //
    // @GetMapping("/logout")
    // public String logout() {
    //     return "redirect:/homepage";
    // }

    // 회원가입 //
    @GetMapping("/membership")
    public String membershipPage() {
        return "membership";
    }
    @PostMapping("/membership")
    public String join(@ModelAttribute CustomerDto dto) {

        customerService.joinCustomerDto(dto);
        return "redirect:/loginpage";
    }
    // 로그인 페이지 //
    @GetMapping("/loginpage")
    public String loginpage(@RequestParam(value = "errorMessage", required = false) String errorMessage, Model model) {
        
        model.addAttribute("errorMessage", errorMessage);
        return "login";
        }
    
    // 유저 홈페이지 //
    @GetMapping("/userhome")
    public String userhome(Authentication authentication, Model model) {
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        
        return "userhome";
    }
    // 관리자 페이지 //
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
    // 마이페이지 //
    @GetMapping("/mypage")
    public String mypage(Authentication authentication, Model model){
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        
        return "mypage";
    }
    // ai 페이지 //
    @GetMapping("/ai")
    public String ai(Authentication authentication, Model model){
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());

        return "ai";
    }
    // 구매정보 페이지 //
    @GetMapping("/buylist")
    public String buylist(Authentication authentication, Model model){
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        
        return "buylist";
    }
    
}
