package com.hrh.micro.interceptor;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.interceptor
 * @ClassName: FeignRequestInterceptor
 * @Author: HuRonghua
 * @Description: Feign拦截器
 * @Date: 2020/3/21 7:03
 * @Version: 1.0
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String header = request.getHeader(name);
                requestTemplate.header(name,header);
            }
        }

        Enumeration<String> parameterNames = request.getParameterNames();

        StringBuilder stringBuilder = new StringBuilder();
        if (parameterNames != null) {
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String parameter = request.getParameter(name);

                // 将令牌放入header
                if ("access_token".equals(name)){
                    requestTemplate.header("authorization", "Bearer " + parameter);
                }
                // 其他参数放入body
                else{
                    stringBuilder.append(name).append("=").append(parameter).append("&");
                }

            }
        }

        if (stringBuilder.length() > 0){
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            requestTemplate.body(Request.Body.bodyTemplate(stringBuilder.toString(), Charset.defaultCharset()));
        }

    }
}
