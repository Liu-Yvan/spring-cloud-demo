package com.git.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {

    @Value("${info.profile:error}")
    private String profile;

    @Value("${info.test:error}")
    private String text;

    @Value("${debug.open:error}")
    private String open;

    @GetMapping("/info")
    public String hello() {
        return profile;
    }

    @GetMapping("/text")
    public String text() {
        return text;
    }

    @GetMapping("/open")
    public String open() {
        return open;
    }


}
