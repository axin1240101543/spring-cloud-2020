package com.darren.center.api.driver.ribbonconfig;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>自定义负载均衡策略</p>
 *
 * @author : Darren
 * @date : 2020年08月13日 11:30:10
 **/
public class MyRandomRule extends AbstractLoadBalancerRule {

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

                /*int index = this.chooseRandomInt(serverCount);
                server = (Server)upList.get(index);*/

                //选自定义元数据的server，选择端口以1或者3结尾的服务。
                for (Server var:upList) {
                    server = var;
                    String hostPort = var.getHostPort();
                    if (hostPort.endsWith("1") || hostPort.endsWith("3")){
                        break;
                    }
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
