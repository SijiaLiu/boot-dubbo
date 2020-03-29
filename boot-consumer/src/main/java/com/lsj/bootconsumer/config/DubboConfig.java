package com.lsj.bootconsumer.config;


import com.google.common.collect.Lists;
import com.lsj.bootprovider.service.DemoService;
import org.apache.dubbo.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
        protocolConfig.setPort(20881);
        return protocolConfig;
    }

    /**
     * 方法级别(无论是消费方还是提供方) > 接口级别 > 全局配置
     * @return
     */
    @Bean
    public DemoService demoService() {
        ReferenceConfig<DemoService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface(DemoService.class);
        // 消费方接口级别的超时
        referenceConfig.setTimeout(300);
        referenceConfig.setRetries(3);

        List<MethodConfig> methodConfigs = Lists.newArrayList();
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("getNameById");
        // 消费方方法级别超时
        methodConfig.setTimeout(500);
        methodConfigs.add(methodConfig);

        referenceConfig.setMethods(methodConfigs);
        return referenceConfig.get();
    }
}
