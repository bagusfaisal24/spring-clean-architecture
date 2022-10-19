package com.clean.architecture.logging;

import com.clean.architecture.service.LoggingSvc;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Component
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class LoggingFilter extends OncePerRequestFilter {

    @Autowired
    private LoggingSvc loggingSvc;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        String responseBody = getStringValue(responseWrapper.getContentAsByteArray());
        String requestBody = getStringValue(requestWrapper.getContentAsByteArray());

        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("method", requestWrapper.getMethod());
            map.put("json_request", requestBody);
            map.put("json_response", responseBody);
            map.put("uri", requestWrapper.getRequestURI());
            map.put("http_status", String.valueOf(responseWrapper.getStatus()));
            loggingSvc.createLog(map, "api-log");
        }catch (Exception e){
            log.error("error logging filter" + e.getMessage());
        }
        responseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentAsByteArray) {
        try {
            return new String(contentAsByteArray, StandardCharsets.UTF_8);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
