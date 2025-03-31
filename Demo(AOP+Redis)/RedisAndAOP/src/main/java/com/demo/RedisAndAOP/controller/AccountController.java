package com.demo.RedisAndAOP.controller;

import com.demo.RedisAndAOP.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.RedisAndAOP.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return service.getAccount(id);
    }
}
