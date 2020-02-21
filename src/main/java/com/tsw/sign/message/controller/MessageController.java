package com.tsw.sign.message.controller;

import com.tsw.sign.commons.BaseResult;
import com.tsw.sign.commons.PageInfo;
import com.tsw.sign.message.modle.Message;
import com.tsw.sign.message.modle.MessageQuery;
import com.tsw.sign.message.service.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping(value = "message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String message(Model model){
        List<Message> messages=messageService.selectAll();
        model.addAttribute("messages",messages);
        return "sign_message";
    }
    @ResponseBody
    @RequestMapping(value = "listdata",method = RequestMethod.GET)
    public List<Message> messageData(){
        List<Message> msg=messageService.selectAll();
        return msg;
    }
    @ResponseBody
    @RequestMapping(value = "selectData",method = RequestMethod.POST)
    public List<Message> selectData(@RequestBody MessageQuery messageQuery){
        List<Message> datas=messageService.selectData(messageQuery);
        return datas;
    }
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String insert() {
        return "message_form";
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<Message> page(HttpServletRequest request, Message message) {

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
        PageInfo<Message> pageInfo = messageService.page(draw, start, length, message);

        return pageInfo;
    }
    //   显示报名信息详情页
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail() {
        return "message_detail";
    }

    @ModelAttribute
//    次注解会在所有的@RequestMapping之前执行
    public Message getById(Long id) {
//     id不为空 则从数据库获取
        Message message = null;
        if (id != null) {
            message = messageService.getById(id);
        } else {
            message = new Message();
        }
        return message;
    }
    //    删除用户信息
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            messageService.deleteMulit(idArray);
            baseResult = BaseResult.success("删除报名信息成功");
        } else {
            baseResult = BaseResult.fail("删除报名信息失败");
        }
        return baseResult;
    }
    //    前端报名页面
    @RequestMapping(value = "post_message", method = RequestMethod.GET)
    public String post() {
        return "register";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save( Model model, Message message, RedirectAttributes redirectAttributes) throws ParseException {
        BaseResult baseResult = messageService.save(message);
            //        添加用户信息成功
            if (baseResult.getStatus() == 200) {
                model.addAttribute("baseResult", baseResult);
                return "register";
            }
            //        添加用户信息失败
            else {
                model.addAttribute("baseResult", baseResult);
                return "register";
            }
        }
    @RequestMapping(value = "renew", method = RequestMethod.POST)
    public String renew(Model model, Message message, RedirectAttributes redirectAttributes,BindingResult bindingResult) throws ParseException {
        BaseResult baseResult = messageService.save(message);
//        添加用户信息成功
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/message/list";
        }
//        添加用户信息失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "message_form";
        }
    }

}
