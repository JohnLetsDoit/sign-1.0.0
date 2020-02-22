package com.tsw.sign.config;

import com.tsw.sign.message.interceptor.MessageInterceptor;
import com.tsw.sign.role.roleinterceptor.RoleInterceptor;
import com.tsw.sign.user.interceptor.LoginInterceptor;
import com.tsw.sign.user.interceptor.MainInterceptor;
import com.tsw.sign.user.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        if(!registry.hasMappingForPattern("/static/**")){
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截的管理器
        LoginInterceptor loginInterceptor=new LoginInterceptor();
        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);     //拦截的对象会进入这个类中进行判断
        registration.addPathPatterns("/**");                    //所有路径都被拦截
        registration.excludePathPatterns("/login","","/login.html","/css/**","/fonts/**","/img/**","/js/**","/static/**","/user/post_message","/message/save");       //添加不拦截路径
        //登陆后无法通过连接进入/login
        InterceptorRegistration regist = registry.addInterceptor(new MainInterceptor());     //拦截的对象会进入这个类中进行判断
        regist.addPathPatterns("/**");
        //通过session限定用户访问路径(用户管理部分）
        PermissionInterceptor permissionInterceptor=new PermissionInterceptor();
        InterceptorRegistration permissionregist = registry.addInterceptor(permissionInterceptor);     //拦截的对象会进入这个类中进行判断
        permissionregist.addPathPatterns("/user/delete","/user/form");
        //通过session限定用户访问路径(角色管理部分）
        RoleInterceptor roleInterceptor=new RoleInterceptor();
        InterceptorRegistration roleregist = registry.addInterceptor(roleInterceptor);     //拦截的对象会进入这个类中进行判断
        roleregist.addPathPatterns("/role/form");
        //通过session限定用户访问路径(信息管理部分）
        MessageInterceptor messageInterceptor=new MessageInterceptor();
        InterceptorRegistration messageregist = registry.addInterceptor(messageInterceptor);     //拦截的对象会进入这个类中进行判断
        messageregist.addPathPatterns("/message/form","/message/post_message","/message/delete");
        messageregist.excludePathPatterns("/user/post_message");
    }
}
