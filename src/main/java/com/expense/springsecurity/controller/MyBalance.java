package com.expense.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyBalance {
    @GetMapping("/mybalance")
    public String getWelcome() {
        return ("My balance");
    }
}
