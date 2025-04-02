package com.demo.BenchmarkNativeCompile.controller;

import com.demo.BenchmarkNativeCompile.service.Calculate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BenchmarkController {

    private final Calculate cal;


    @Autowired
    public BenchmarkController(Calculate cal) {
        this.cal = cal;
    }

    @GetMapping("/benchmark")
    public Map<String, Object> benchmark() {
        return cal.process();
    }

}
