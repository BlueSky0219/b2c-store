package com.sky.admin.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bluesky
 * @create 2022-11-22-16:46
 */
@Component
public class LoginProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // false 拦截

        Object userInfo = request.getSession().getAttribute("userInfo");

        if (userInfo != null) {

            // 放行
            return true;
        }else {

            response.sendRedirect(request.getContextPath()+"/");
            return false;
        }


    }
}
