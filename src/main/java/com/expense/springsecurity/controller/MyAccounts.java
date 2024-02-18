package com.expense.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAccounts {
    @GetMapping("/myaccount")
    public String AccountDetails() {
        return "My account";
    }
}
