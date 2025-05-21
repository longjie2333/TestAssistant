package com.our.testassistantback.interceptor;

import com.alibaba.fastjson.JSONObject;

import com.our.testassistantback.pojo.Result;
import com.our.testassistantback.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");

        String url = request.getRequestURL().toString();
        log.info("url:{}",url);

        if(url.contains("login")||url.contains("register")){
            log.info("登录操作,放行...");
            return true;
        }

        String jwt = request.getHeader("Authorization");

        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");

            String s = JSONObject.toJSONString(error);
            response.getWriter().write(s);
            return false;
        }

        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("解析令牌失败，返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            String s = JSONObject.toJSONString(error);
            response.getWriter().write(s);
            return false;
        }

        log.info("令牌合法，放行");
       return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("afterCompletion");
    }
}
