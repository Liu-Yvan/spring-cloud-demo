package com.eureka.consumer.hystrix;

import com.eureka.consumer.feign.HelloRemoteFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HelloRemoteHystrix implements HelloRemoteFeign {
    @Override
    public String hello(@RequestParam(value = "name") String name) {
        return "Hello World!";
    }

    @Override
    public String sub(@RequestParam(value = "name") String name) {
        return "Hello Sub!";
    }
}
