package com.jd.platform.worker.config;

import com.jd.platform.common.coder.Codec;
import com.jd.platform.common.coder.NettyCodec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * netty的编解码器
 * @author wuweifeng wrote on 2020-02-21
 * @version 1.0
 */
@Configuration
public class CodecConfig {

    @Bean
    public Codec codec() {
        return new NettyCodec();
    }
}
