package com.darren.center.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>限流Filter</p>
 * http://localhost:9000/actuator/filters
 *
 * ZuulServlet中通过service方法，可以看出整个servlet的处理流程：
 * pre异常: pre -> error -> post
 * route异常： pre -> route -> error -> post
 * post异常： pre -> route -> post -> error
 * 正常: pre -> route -> post
 * 为什么最后都要走post，因为post最后，才能直接给用户响应数据。
 * pre:表示路由的前置过滤器链，route:表示路由的过滤器链，post:表示路由的后置过滤器链，error：表示路由错误过滤器链。
 * 由此可见，责任链模式是zuul的核心。
 *
 *
 * http://localhost:9000/api/zuul-api-driver/test/hello?name=darren&age=18
 * header: token 123456
 * header: Authorization ''
 *
 * @author : Darren
 * @date : 2020年08月19日 17:12:15
 **/
@Slf4j
@Component
public class RateFilter extends ZuulFilter {

    /**
     * 如果是1，表示每秒1个令牌，实际通过压测获得
     *
     * 1、创建一个稳定输出令牌的RateLimiter，保证了平均每秒不超过permitsPerSecond个请求
     * 2、当请求到来的速度超过了permitsPerSecond，保证每秒只处理permitsPerSecond个请求
     * 3、当这个RateLimiter使用不足(即请求到来速度小于permitsPerSecond)，会囤积最多permitsPerSecond个请求
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(5);

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -10;
    }

    @Override
    public boolean shouldFilter() {
        String requestURI = getRequest().getRequestURI();
        log.info("限流Filter：{}", requestURI);
        //可以判断地址。是否限流
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        /**
         * 拿不到令牌马上返回。尝试获取桶里的令牌，如果有，则返回true，
         *并且，总的令牌数减1。没有则返回false。
         */
        if (!RATE_LIMITER.tryAcquire()){
            log.info("rate filter 拿不到令牌，被限流了:{}", count.addAndGet(1));
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }
        currentContext.setSendZuulResponse(true);
        return null;
    }

    /**
     * 获取上下文（重要，贯穿 所有filter，包含所有参数）
     * @return
     */
    private HttpServletRequest getRequest(){
        RequestContext currentContext = RequestContext.getCurrentContext();
        return currentContext.getRequest();
    }
}
