package com.demo.BenchmarkNativeCompile.service;

import com.demo.BenchmarkNativeCompile.log.StartupTime;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Calculate {

    private static final long appStartTime = System.nanoTime();

    private static long startupTime = 0;

    public Map<String, Object> process() {

        long now = System.nanoTime();

        if (startupTime == 0){
            startupTime = (now - appStartTime) / 1_000_000;
        }

        long start = System.nanoTime();

        long result = TestCase();

        long end = System.nanoTime();

        long durationTime = (end - start) / 1_000_000;

        String mode = System.getProperty("org.graalvm.nativeimage.imagecode") != null ? "Native" : "JVM";

        Map<String, Object> response = new HashMap<>();
        response.put("result", result);
        response.put("calculation_time", durationTime+" ms");
        response.put("startup_time", StartupTime.getStartupTimeFormatted());
        response.put("runMode", mode);

        return response;
    }

    public long TestCase() {
        long sum = 0;
        for (int i = 0; i < 100_000_000; i++) {
            sum += i;
        }
        return sum;
    }

}
