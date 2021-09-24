package com.spring.demo.interceptor;

import com.oracle.tools.packager.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);
    private static final String START_TIME_REQUEST_ATT = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME_REQUEST_ATT,startTime);
        log.info("Request prehandle {} started at {}", request.getRequestURL(),startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Request posthandle {}", request.getRequestURL());
        if(modelAndView != null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            modelAndView.addObject("userName",authentication.getName());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (long) request.getAttribute(START_TIME_REQUEST_ATT);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        log.info("Response afterCompletion {} : {} ms to proceed", request.getRequestURL(),duration);
    }
}
