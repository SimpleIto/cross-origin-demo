package top.simpleito.crossorigindemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于处理非简单跨域请求的OPTIONS方法的预检请求
 */
@Configuration
public class TestCrosWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                if(request.getMethod().equals("OPTIONS")&&request.getHeader("Origin").equals("http://localhost:3000")) {
                    response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
                    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT");
                    response.addHeader("Access-Control-Allow-Headers", "Content-Type");
                }
                return true;
            }
        };
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns("/test-cross-annotation");
    }
}
