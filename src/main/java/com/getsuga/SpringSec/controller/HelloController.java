package com.getsuga.SpringSec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController{

    @GetMapping("/")
    public String greet(HttpServletRequest request){
        return "if you wanna see some action, you gotta be the center of attraction " + request.getSession().getId();
    }


}
