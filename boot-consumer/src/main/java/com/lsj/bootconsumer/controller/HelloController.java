package com.lsj.bootconsumer.controller;


import com.lsj.bootprovider.service.DemoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    private DemoService demoService;

    @RequestMapping("/hello")
    @HystrixCommand(fallbackMethod = "fallback")
    public String getNameById(@RequestParam(value = "id") String id) {
        System.out.println(id);
        return demoService.getNameById(id);
    }

    public String fallback() {
        System.out.println("fallback");
        return "fallback";
    }

}
