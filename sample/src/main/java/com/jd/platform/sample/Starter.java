package com.jd.platform.sample;

import com.jd.platform.client.ClientStarter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wuweifeng wrote on 2020-01-14
 * @version 1.0
 */
@Component
public class Starter {
    @Value("${etcd.server}")
    private String etcd;
    @Value("${spring.application.name}")
    private String appName;

    @PostConstruct
    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ClientStarter.Builder builder = new ClientStarter.Builder();
                ClientStarter starter = builder.setAppName(appName).setEtcdServer(etcd).build();
                starter.startPipeline();
            }
        }).start();

    }
}
