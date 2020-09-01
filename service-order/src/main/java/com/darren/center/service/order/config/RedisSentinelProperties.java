package com.darren.center.service.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>redis哨兵配置类</p>
 *
 * application.yml中配置
 * #自定义哨兵
 * sentinel:
 *   address:
 *     - 127.0.0.1:26379
 *     - 127.0.0.1:26380
 *     - 127.0.0.1:26381
 *   masterName: mymaster
 *
 * @author : Darren
 * @date : 2020年08月31日 16:47:59
 **/
@Component
@ConfigurationProperties(prefix = "sentinel")
@Order(0)
@Data
public class RedisSentinelProperties {

    /**
     * 哨兵的地址
     */
    private String[] address;

    /**
     * 主的逻辑名称（一套哨兵可以监控多个集群）
     */
    private String masterName;

}
