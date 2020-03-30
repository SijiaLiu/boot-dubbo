package com.lsj.bootprovider;

import com.lsj.bootprovider.service.DemoService;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import java.io.IOException;

@DubboComponentScan(basePackageClasses = {DemoService.class})
@SpringBootApplication
@EnableHystrix
public class BootProviderApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BootProviderApplication.class, args);
        System.out.println("Dubbo service start!");
        System.in.read();
    }

}
