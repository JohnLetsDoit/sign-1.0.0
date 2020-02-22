package com.tsw.sign.user.controller;

import com.tsw.sign.user.model.User;
import com.tsw.sign.user.service.UserService;
import com.tsw.sign.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping(value = {"","login"}  )
    public String loginView(){
        return "login";
    }
    @PostMapping(value = "login")
    public String login(@RequestParam(required = true)String username, @RequestParam(required = true) String password, HttpServletRequest httpServletRequest, Model model){
        User user=userService.login(username,password);
        String specific="ftgb";
        if (user==null){
            model.addAttribute("message","用户名或密码错误，请重新输入");
            return loginView();
        }else if (user.getUsername().equals(specific)){
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER,user);
            model.addAttribute("greet","欢迎你！！！");
            model.addAttribute("specific","");
            model.addAttribute("specificfail","");
            model.addAttribute("specificsuccess","");
            return "main";
        }
        else {
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER,user);
//            model.addAttribute("user",user);
            return "main";
        }
    }
    /**
     * 注销
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        return loginView();
    }
}