package com.outrun.controller;

import com.outrun.XxProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${xxx}")
    private String xxx;

    @Autowired
    private XxProperties xxProperties;

    @RequestMapping("/hello")
    public String hello() {
        return "hello" + xxx + xxProperties.getYy();
    }
}