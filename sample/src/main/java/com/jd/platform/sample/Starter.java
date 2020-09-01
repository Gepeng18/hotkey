package com.jd.platform.sample;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.base.Joiner;
import com.ibm.etcd.api.KeyValue;
import com.jd.platform.hotkey.client.ClientStarter;
import com.jd.platform.hotkey.client.callback.JdHotKeyStore;
import com.jd.platform.hotkey.common.configcenter.IConfigCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

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

    @Resource
    private IConfigCenter iConfigCenter;
    private Logger logger = LoggerFactory.getLogger(getClass());

    private AtomicLong counter = new AtomicLong();


    @PostConstruct
    public void init() {
        ClientStarter.Builder builder = new ClientStarter.Builder();
        ClientStarter starter = builder.setAppName(appName).setEtcdServer(etcd).build();
        starter.startPipeline();

        List<KeyValue> list = iConfigCenter.getPrefix("/jd/workers/sample/host");
        for (KeyValue keyValue : list) {
            System.out.println(keyValue.getKey() + keyValue.getValue().toStringUtf8());
        }
        //监听巨大的key
//        watchBigKey();

        ExecutorService executorService = Executors.newFixedThreadPool(128);


        StringBuilder builder1 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            builder1.append(UUID.randomUUID());
        }

        a();
//        logger.info("begin : " + System.currentTimeMillis());
//        iConfigCenter.putAndGrant( "/abc/" + builder1.toString(), UUID.randomUUID().toString(), 60);
//        for (int i = 0; i < 200000; i++) {
//            final int j = i;
//            executorService.submit(() -> {
//                    iConfigCenter.putAndGrant( ConfigConstant.hotKeyRecordPath + "abc/" + j, UUID.randomUUID().toString(), 10000);
//                    counter.incrementAndGet();
//                    if (counter.get() % 5000 == 0) {
//                        logger.info("counter:" + counter.get());
//                    }
//            });
//        }
//        a();
    }

//    private void watchBigKey() {
//        CompletableFuture.runAsync(() -> {
//            KvClient.WatchIterator watchIterator = iConfigCenter.watchPrefix("/abc");
//            //如果有新事件，即rule的变更，就重新拉取所有的信息
//            while (watchIterator.hasNext()) {
//                //这句必须写，next会让他卡住，除非真的有新rule变更
//                Event event = watchIterator.next().getEvents().get(0);
//                double m = ObjectSizeCalculator.getObjectSize(event.getKv().getKey().toStringUtf8());
//                logger.error("length:" + m);
//            }
//
//        });
//    }

    private void a() {
        System.out.println("begin");
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (JdHotKeyStore.getValue("a") != null) {
                System.out.println("ishot");
            } else {
                JdHotKeyStore.smartSet("a", 1);
//                logger.error("smartSet");
            }
        }

    }


    public static void main(String[] args) {
        Joiner joiner = Joiner.on(",");
        String s = "1";
        List<String> list = CollectionUtil.list(true, s);
        String j = joiner.join(list);
        System.out.println(j);

        String[] ss = j.split(",");
        System.out.println(ss);
    }
}
