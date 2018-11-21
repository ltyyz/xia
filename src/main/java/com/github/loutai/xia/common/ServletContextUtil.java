package com.github.loutai.xia.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextUtil {

    public static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletRequest getCurrentRequest() {
        return getServletRequestAttributes().getRequest();
    }

    public static HttpServletResponse getCurrentResponse() {
        return getServletRequestAttributes().getResponse();
    }
}
