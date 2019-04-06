package com.demo.project68;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application {

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    CommandLineRunner runner(MeterRegistry mr) {
        return args -> {

            this.executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println("test");
                    int sleepTime = new Random().nextInt(60);
                    mr.timer("transform-photo-task").record(Duration.ofSeconds(sleepTime));
                }
            }, 5, 5, TimeUnit.SECONDS);
        };
    }

}
