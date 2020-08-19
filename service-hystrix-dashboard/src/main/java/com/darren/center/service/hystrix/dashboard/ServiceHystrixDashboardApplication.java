package com.darren.center.service.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * http://localhost:8888/hystrix  #监控面板
 *
 * 前提：开启Admin监控
 * http://localhost:8080/actuator/hystrix.stream、http://localhost:8081/actuator/hystrix.stream #只能监控一个服务，实际生产中不方便。
 * http://localhost:8888/turbine.stream #也是一直ping，相当于原来的hystrix.stream,不过此处是综合了所有的项目
 *
 * Turbine的坑：
 * https://www.cnblogs.com/houzheng/p/9906800.html
 */
@SpringBootApplication
@EnableEurekaClient //表示此工程可以向注册中心提供服务
@EnableHystrix //表示开启熔断器
@EnableHystrixDashboard //表示开启熔断监控
@EnableTurbine //表示开启监控数据聚合
public class ServiceHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHystrixDashboardApplication.class, args);
    }

}
