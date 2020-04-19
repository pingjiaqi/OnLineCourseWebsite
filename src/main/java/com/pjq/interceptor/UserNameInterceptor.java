package com.pjq.interceptor;


import com.pjq.controller.UserController;
import com.pjq.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


/**
 * @author pjq
 */
@Component
@Slf4j
public class UserNameInterceptor implements HandlerInterceptor {

    @Resource
    UserController userController;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null) {
            Result result = new Result();
            result.setMessage("未登录");
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.sendRedirect("/user/unLogin");
            System.out.println("Intercept");
            return false;
        }
        else {
                System.out.println(username);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
