package com.tsw.sign.user.controller;


import com.tsw.sign.commons.BaseResult;
import com.tsw.sign.commons.PageInfo;
import com.tsw.sign.user.model.User;
import com.tsw.sign.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<User> users = userService.selectAll();
        model.addAttribute("users", users);
        return "user_list";
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String insert() {
        return "user_form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Model model, User user, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = userService.save(user);
//        添加用户信息成功
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/user/list";
        }
//        添加用户信息失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "user_form";
        }
    }
    @ModelAttribute
//    次注解会在所有的@RequestMapping之前执行
    public User getById(Long id) {
//     id不为空 则从数据库获取
        User user = null;
        if (id != null) {
            user = userService.getById(id);
        } else {
            user = new User();
        }
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<User> page(HttpServletRequest request, User user) {

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        /**f封装datatables需 要的结果
         *@Author John【chinacode@yeah.net】
         *@Date
         */
        PageInfo<User> pageInfo = userService.page(draw, start, length, user);
        return pageInfo;
    }
    //    删除用户信息
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            userService.deleteMulit(idArray);
            baseResult = BaseResult.success("删除用户成功");
        } else {
            baseResult = BaseResult.fail("删除用户失败");
        }
        return baseResult;
    }
//   显示用户详情页
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail() {
        return "user_detail";
    }
//    前端报名页面
    @RequestMapping(value = "post_message", method = RequestMethod.GET)
    public String post() {
        return "register";
    }
}
