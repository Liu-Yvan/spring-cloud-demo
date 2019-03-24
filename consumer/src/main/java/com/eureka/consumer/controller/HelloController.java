package com.eureka.consumer.controller;

import com.eureka.consumer.feign.HelloRemoteFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Resource
    HelloRemoteFeign helloRemoteFeign;

    @GetMapping("/{name}")
    public String hello(@PathVariable("name") String name) {
        return helloRemoteFeign.hello(name + "!");
    }


    @GetMapping("/sub/{name}")
    public String sub(@PathVariable("name") String name) {
        return helloRemoteFeign.sub(name + "!");
    }
}
