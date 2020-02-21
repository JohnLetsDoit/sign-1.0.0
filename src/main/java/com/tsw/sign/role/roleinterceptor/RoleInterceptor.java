package com.tsw.sign.role.roleinterceptor;

import com.tsw.sign.user.model.User;
import com.tsw.sign.utils.ConstantUtils;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler)throws Exception {
        User user=(User) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
        String permission="一般管理";
        if (user.getRole().equals(permission)){
            httpServletResponse.sendRedirect("/permission");
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
