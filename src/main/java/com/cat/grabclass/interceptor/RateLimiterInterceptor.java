package com.cat.grabclass.interceptor;

import com.cat.grabclass.common.contants.ResultStatus;
import com.cat.grabclass.excption.BusinessException;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Mr.xin
 */
@Component("rateLimiterInterceptor")
public class RateLimiterInterceptor implements HandlerInterceptor {
    private RateLimiter rateLimiter;

    @PostConstruct
    public void init() {
        rateLimiter = RateLimiter.create(100);
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;
            com.cat.grabclass.common.annotations.RateLimiter annotation = hm.getMethodAnnotation(com.cat.grabclass.common.annotations.RateLimiter.class);
            if(annotation != null){
                if(!rateLimiter.tryAcquire(1)){
                    throw new BusinessException(ResultStatus.RATELIMITE);
                }
            }
        }
        return true;
    }
}
