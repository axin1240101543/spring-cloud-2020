package com.darren.center.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>测试Pre Filter</p>
 *
 * @author : Darren
 * @date : 2020年08月19日 15:47:00
 **/
@Slf4j
@Component
public class PreFilter extends ZuulFilter {

    /**
     * filter的类型
     * PRE: 在请求被路由之前调用，可利用这种过滤器实现身份验证。选择微服务，记录日志。
     * ROUTING:在将请求路由到微服务调用，用于构建发送给微服务的请求，并用http clinet（或者ribbon）请求微服务。
     * POST:在调用微服务执行后。可用于添加header，记录日志，将响应发给客户端。
     * ERROR:在其他阶段发生错误是，走此过滤器。
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 执行顺序：
     * 在谁前，在谁后，可以+1，-1，越小越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 4;
    }

    /**
     * 此过滤器是否执行：true  false
     * 可以写过滤器是否执行的判断条件。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        String requestURI = getRequest().getRequestURI();
        log.info("PreFilter：{}", requestURI);
        //返回false，表示当前过滤器不执行
        return false;
    }

    /**
     * 具体执行逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        log.info("PreFilter begin");
        String token = getRequest().getHeader("token");
        log.info("PreFilter token:{}", token);
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
