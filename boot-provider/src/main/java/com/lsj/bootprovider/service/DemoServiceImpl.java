package com.lsj.bootprovider.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class DemoServiceImpl implements DemoService {

    @Value("${application.name}")
    private String serviceName;

    @Override
    public String getNameById(String name) {
        System.out.println(name);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.format("[%s] : Hello, %s", serviceName, name);
    }
}
