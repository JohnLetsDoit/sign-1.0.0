package com.tsw.sign.role.controller;

import com.tsw.sign.commons.BaseResult;
import com.tsw.sign.role.model.Role;
import com.tsw.sign.role.service.RoleService;
import com.tsw.sign.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<Role> rolrs=roleService.selectAll();
        return "role_list";
    }
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String insert() {
        return "role_form";
    }
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Model model, Role role, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = roleService.save(role);
//        添加用户信息成功
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/role/list";
        }
//        添加用户信息失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "role_form";
        }
    }
    @ModelAttribute
//    次注解会在所有的@RequestMapping之前执行
    public Role getById(Long id) {
//     id不为空 则从数据库获取
        Role role = null;
        if (id != null) {
            role = roleService.getById(id);
        } else {
            role=new Role();
        }
        return role;
    }
}
