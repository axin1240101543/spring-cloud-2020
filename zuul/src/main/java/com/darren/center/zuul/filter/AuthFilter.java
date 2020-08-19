package com.darren.center.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>鉴权Filter</p>
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
 *
 * @author : Darren
 * @date : 2020年08月19日 16:02:27
 **/
@Slf4j
@Component
public class AuthFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public boolean shouldFilter() {
        String requestURI = getRequest().getRequestURI();
        log.info("鉴权Filter：{}", requestURI);
        //只有此接口/zuul-api-driver/test/hello才被拦截
        String checkUri = "/zuul-api-driver/test/hello";
        if (requestURI.endsWith(checkUri)){
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("AuthFilter begin");
        RequestContext currentContext = RequestContext.getCurrentContext();
        String authorization = currentContext.getRequest().getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization)){
            log.info("auth filter:认证通过");
        }else{
            log.info("auth filter:认证失败");
            //不往下的过滤器继续了
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            currentContext.setResponseBody("认证失败");
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
