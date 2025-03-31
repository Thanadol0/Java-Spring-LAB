package com.demo.RedisAndAOP.repository;

import com.demo.RedisAndAOP.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
