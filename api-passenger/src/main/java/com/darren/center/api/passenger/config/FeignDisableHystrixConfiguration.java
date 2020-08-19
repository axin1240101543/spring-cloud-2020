package com.darren.center.api.passenger.config;

import com.darren.center.api.passenger.annotation.ExcludeFeignConfig;
import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>禁用feign客户端的hystrix</p>
 * 为@feignclient单独配置Feign.Builder
 *
 * @author : Darren
 * @date : 2020年08月18日 15:23:45
 **/
@ExcludeFeignConfig
@Configuration
public class FeignDisableHystrixConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder(){
        //禁用feign客户端的hystrix
        return Feign.builder();
        //给feign配置hystrix的属性 https://blog.csdn.net/weixin_33788244/article/details/86013793
        /*return HystrixFeign.builder().setterFactory(new SetterFactory() {
            @Override
            public HystrixCommand.Setter create(Target<?> target, Method method) {
                return HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(SmsClient.class.getSimpleName()))// 控制ShortMsgService下,所有方法的Hystrix Configuration
                        .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(200));//配置线程池大小,默认值10个
            }
        });*/
    }

}
