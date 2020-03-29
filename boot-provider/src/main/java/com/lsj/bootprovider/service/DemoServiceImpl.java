package com.lsj.bootprovider.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.Random;


@Service
public class DemoServiceImpl implements DemoService {

    @Value("${application.name}")
    private String serviceName;

    @Override
    @HystrixCommand
    public String getNameById(String name) {
        System.out.println(name);
        System.out.println("20882");
        if (new Random().nextBoolean()) {
            throw new RuntimeException();
        }
        return String.format("[%s] : Hello, %s", serviceName, name);
    }
}
