package com.fastcampus.springboot_study.ch2;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Arrays;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String main(){
        return "index";
    }

    @GetMapping("/test")
    public String test(Model model, HttpServletRequest request){
        request.setAttribute("year",2022); // request객체에 저장

        HttpSession session = request.getSession(); // 요청으로 부터 session객체를 얻어옴
        session.setAttribute("id","asdf"); // session객체에 id를 저장

        ServletContext application = session.getServletContext();
        application.setAttribute("email","service@fastcampus.com");

        model.addAttribute("lastName","KyungSik");
        model.addAttribute("firstName","Kim");
        model.addAttribute("list", Arrays.asList("aaa","bbbb","cccc","dddd"));
        model.addAttribute("bno",123);
        model.addAttribute("user",new User("aaa",20));
        return "test";
    }
}
