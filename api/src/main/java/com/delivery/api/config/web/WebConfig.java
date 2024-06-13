package com.delivery.api.config.web;

import com.delivery.api.interceptor.AuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthorizationInterceptor authorizationInterceptor;
    private List<String> OPEN_API = List.of(    // 검증이 필요 없는 uri
            "/open-api/**"
    );

    private List<String> DEFAULT_EXCLUDE = List.of(     // 디폴트로 검증 필요없는 uri 들
            "/",
            "favicon.ico",
            "/error"
    );

    private List<String> SWAGGER = List.of(     // Swagger uri
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/v3/api-docs/**"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
                // 검증이 필요없는 uri 제외
                .excludePathPatterns(OPEN_API)
                .excludePathPatterns(DEFAULT_EXCLUDE)
                .excludePathPatterns(SWAGGER);
    }
}
