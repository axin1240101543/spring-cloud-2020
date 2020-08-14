package com.darren.center.api.driver.ribbonconfig;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>自定义负载均衡策略</p>
 * 按照流量分发（60%到A，40%到B）
 * @author : Darren
 * @date : 2020年08月13日 11:30:10
 **/
public class MyRandomRule2 extends AbstractLoadBalancerRule {

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    public Server choose(ILoadBalancer lb, Object key){
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }
                //激活可用的服务
                List<Server> upList = lb.getReachableServers();
                //所有的服务
                List<Server> allList = lb.getAllServers();
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

                //将60%流量分发到A，将40%到B
                Random random = new Random();
                final int number = random.nextInt(10);
                if (number < 7){
                    server = upList.get(0);
                }else {
                    server = upList.get(1);
                }

                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }
}
