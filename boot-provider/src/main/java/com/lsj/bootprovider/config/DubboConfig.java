package com.lsj.bootprovider.config;


import com.google.common.collect.Lists;
import com.lsj.bootprovider.service.DemoService;
import org.apache.dubbo.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 使用注解的方式配置dubbo
 * 如果application.yml文件中配置了 优先读yml的配置, 而且会有冲突
 */
@Configuration
public class DubboConfig {

    @Bean
    public ApplicationConfig ApplicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("localhost:2181");
        registryConfig.setProtocol("zookeeper");
        registryConfig.setCheck(false);
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20882);
        return protocolConfig;
    }

    /**
     * 暴露这个服务
     * 可以这配置方法级别的超时时间等信息。
     * 方法级别(无论是消费方还是提供方) > 接口级别 > 全局配置
     * @param demoService
     * @return
     */
    @Bean
    public ServiceConfig<DemoService> serviceConfig(DemoService demoService) {
        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(demoService);
        // 提供方接口级别超时
        serviceConfig.setTimeout(3000);

        List<MethodConfig> methodConfigs = Lists.newArrayList();
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("getNameById");
        // 提供方方法级别超时
        methodConfig.setTimeout(300);
        methodConfigs.add(methodConfig);

        serviceConfig.setMethods(methodConfigs);
        // 集群容错模式
        // serviceConfig.setCluster();
        return serviceConfig;
    }
}
