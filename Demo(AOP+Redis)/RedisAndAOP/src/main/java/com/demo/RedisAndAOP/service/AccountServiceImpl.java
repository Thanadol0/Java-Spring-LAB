package com.demo.RedisAndAOP.service;

import com.demo.RedisAndAOP.entity.Account;
import com.demo.RedisAndAOP.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository repo;
    private final RedisTemplate<String, Account> redisTemplate;


    @Override
    public Account getAccount(Long id) {
        String key = "account::" + id;
        ValueOperations<String, Account> ops = redisTemplate.opsForValue();

        Account acc = ops.get(key);
        if (acc != null) {
            System.out.println("Fetched from Redis");
            return acc;
        }

        acc = repo.findById(id).orElseThrow();
        ops.set(key, acc, Duration.ofMinutes(5));
        System.out.println("Fetched from DB and cached");
        return acc;
    }


}
