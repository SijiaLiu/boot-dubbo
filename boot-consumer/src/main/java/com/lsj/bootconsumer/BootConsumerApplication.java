package com.lsj.bootconsumer;

import com.lsj.bootprovider.service.DemoService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootApplication
public class BootConsumerApplication {

    @Resource
    private DemoService demoService;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BootConsumerApplication.class);
        System.in.read();
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            System.out.println(demoService.getNameById("liusijia"));
        };
    }

}
