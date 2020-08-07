package com.example.mystuff.backend.controller;

import com.example.mystuff.backend.entity.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/hello")
public class HelloController {

    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/en")
    public @ResponseBody
    String index() {
        return "Greetings from Spring Boot!";
    }

    // Provide a name query string parameter by visiting
    // http://localhost:8080/greeting?name=User.
    @GetMapping("/greeting")
    public @ResponseBody
    Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}