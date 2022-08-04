package com.demo.project68;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

@RestController
@RequestMapping("/api")
@Slf4j
class AppController {

    @Timed("hello.api.time")
    @GetMapping("/hello")
    public String sayHello() throws InterruptedException {
        RegistryConfig.helloApiCounter.increment();
        int sleepTime = new Random().nextInt(10);
        log.info("Sleeping for seconds: {}", sleepTime);
        TimeUnit.SECONDS.sleep(sleepTime);
        return "Hello, Sleep for " + sleepTime + " Seconds!";
    }
}

@Configuration
@EnableAspectJAutoProxy
class RegistryConfig {

    public static Counter helloApiCounter;

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
        return registry -> registry.config().commonTags("application", applicationName);
    }

    @PostConstruct
    public void postInit() {
        helloApiCounter = Metrics.counter("hello.api.count", "type", "order");
    }
}
