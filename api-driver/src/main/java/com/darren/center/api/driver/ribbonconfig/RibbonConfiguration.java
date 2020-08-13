package com.darren.center.api.driver.ribbonconfig;

import com.darren.center.api.driver.annotation.ExcudeRibbonConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>该类不应该在主应用程序的扫描之下，需要修改启动类的扫描配置。
 * 否则将被所有的Ribbon client共享，比如此例中：ribbonRule 对象 会共享。</p>
 *
 * @author : Darren
 * @date : 2020年08月13日 09:33:55
 **/
@ExcudeRibbonConfig
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }

}
