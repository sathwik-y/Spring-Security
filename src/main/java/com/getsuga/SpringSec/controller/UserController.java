package com.getsuga.SpringSec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.getsuga.SpringSec.model.Users;
import com.getsuga.SpringSec.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return service.register(user);
    }

    @PostMapping("/log")
    public String login(@RequestBody Users user){
        return service.verify(user);
    }
}
