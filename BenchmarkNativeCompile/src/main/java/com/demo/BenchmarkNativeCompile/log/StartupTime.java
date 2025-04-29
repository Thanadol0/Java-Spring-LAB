package com.demo.BenchmarkNativeCompile.log;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupTime implements ApplicationListener<ApplicationReadyEvent> {

    private static long startNano = System.nanoTime();

    private static long startupNano = -1;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        startupNano = System.nanoTime() - startNano;
        System.out.println("[StartupTime] App started in " + getStartupTimeFormatted());
    }

    public static String getStartupTimeFormatted() {
        if (startupNano < 0) return "Not measured";
        return String.format("%.3f seconds", startupNano / 1_000_000_000.0);
    }



}
