package com.eureka.consumer.feign;


import com.eureka.consumer.hystrix.HelloRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eureka-producer", fallback = HelloRemoteHystrix.class)
public interface HelloRemoteFeign {

    @GetMapping("/hello/")
    String hello(@RequestParam(value = "name") String name);

    @GetMapping("/hello/sub/")
    String sub(@RequestParam(value = "name") String name);

}
