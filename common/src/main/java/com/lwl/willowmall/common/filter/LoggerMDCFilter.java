package com.lwl.willowmall.common.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * @author pulin
 * 日志过滤器，埋点处理
 */
public class LoggerMDCFilter implements Filter {
    public static final String IDENTIFIER = "IDENTIFIER";
    public static final String URI_KEY = "URI";

    private String getId(String s) {
        return String.format("%s@", s);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestId = req.getHeader("requestId");
        if (StringUtils.isBlank(requestId)) {
            requestId = getId(UUID.randomUUID().toString());
        }else{
            requestId = getId(requestId);
        }
        // 唯一标识埋点，设置线程唯一标识，方便查找日志，组合方式 时间戳@请求埋点@线程号
        MDC.put(IDENTIFIER, requestId);
        // uri_key
        MDC.put(URI_KEY, req.getRequestURI());
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove(IDENTIFIER);
            MDC.remove(URI_KEY);
        }
    }


    @Override
    public void destroy() {

    }


}
