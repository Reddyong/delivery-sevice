package com.delivery.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;


@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /**
         * 뒷 단의 Controller 나 Interceptor 와 같은 곳에 랩핑된 요청 객체가 넘어가게 된다.
         */
        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        log.info("INIT URI : {}", req.getRequestURI());

        // 아래의 doFilter 를 기준으로 실행 전/후가 나뉘게 된다.(윗쪽 코드가 실행 전, 아랫쪽 코드가 실행 후)
        chain.doFilter(req, res);

        // request 정보
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey -> {
            var headerValue = req.getHeader(headerKey);

            // ex) authorization-token : ???? , user-agent : ????
            headerValues
                    .append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("] ");
        });

        var requestBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();

        log.info(">>>>> uri : {}, method : {}, header : {}, body : {}", uri, method, headerValues, requestBody);

        // response 정보
        var responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);

            responseHeaderValues
                    .append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("] ");
        });

        var responseBody = new String(res.getContentAsByteArray());

        log.info("<<<<< uri : {}, method : {}, header : {}, body : {}", uri, method, responseHeaderValues, responseBody);

        // res 의 body 를 읽었기 때문에 초기화 (안 쓰면 response body 가 비어진 상태로 나가게 된다.)
        res.copyBodyToResponse();
    }
}
