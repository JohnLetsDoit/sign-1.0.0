package com.tsw.sign.message.service.impl;

import com.tsw.sign.commons.BaseResult;
import com.tsw.sign.commons.PageInfo;
import com.tsw.sign.message.dao.MessageMapper;
import com.tsw.sign.message.modle.Message;
import com.tsw.sign.message.modle.MessageQuery;
import com.tsw.sign.message.service.MessageService;
import com.tsw.sign.utils.RegexpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl  implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void delete(Long id) {
        messageMapper.delete(id);
    }

    @Override
    public void insert(Message message) {
        messageMapper.insert(message);
    }

    @Override
    public void update(Message message) {
        messageMapper.update(message);
    }

    @Override
    public void deleteMulit(String[] ids) {
        messageMapper.deleteMulit(ids);
    }

    @Override
    public int count(Message message) {
        return messageMapper.count(message);
    }

    @Override
    public int ishave(Message message) {
        return messageMapper.ishave(message);
    }

    @Override
    public Message getById(Long id) {
        return messageMapper.getById(id);
    }

    @Override
    public PageInfo<Message> page(int draw, int start, int length, Message message) {
        int count=messageMapper.count(message);
        Map<String,Object> params=new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("draw",draw);
        params.put("message",message);



        PageInfo<Message> pageInfo=new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setRecordsTotal(count);
        pageInfo.setData(messageMapper.page(params));

        return pageInfo;
    }

    @Override
    public List<Message> selectData(MessageQuery messageQuery) {
        return messageMapper.selectData(messageQuery);
    }

    @Override
    public List<Message> selectAll() {
        return messageMapper.selectAll();
    }

    @Override
    public BaseResult save(Message message) throws ParseException {
        BaseResult baseResult=checkMessage(message);
        message.setSignTime(new Date());
        if (baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            if (message.getId() == null) {
                message.setSignTime(new Date());
                messageMapper.insert(message);
            }
            else {
                message.setSignTime(new Date());
                messageMapper.update(message);


            }
            baseResult.setMessage("报名成功！");
        }
        return baseResult;
    }

    /**报名信息有效性验证
     *@Author John【chinacode@yeah.net】
     *@Date
     */
    private BaseResult

    checkMessage(Message message) throws ParseException {
//        默认是成功的
        BaseResult baseResult=BaseResult.success();
        int counts=messageMapper.ishave(message);
        if (counts>0&&message.getId()==null){
            baseResult.setMessage("对不起！该用户已存在");
            baseResult.setStatus(500);}
        else if(StringUtils.isBlank(message.getSignname())) {
            baseResult=baseResult.fail("姓名不能为空，请重新输入");
        }
        else if(StringUtils.isBlank(message.getGender())) {
            baseResult=baseResult.fail("性别不能为空，请重新输入");
        }
        else if(StringUtils.isBlank(message.getBirthPlace())) {
            baseResult=baseResult.fail("所在省份不能为空，请重新输入");
        }
        else if(StringUtils.isBlank(message.getCollege())) {
            baseResult=baseResult.fail("学院不能为空，请重新输入");
        }
        else if(StringUtils.isBlank(message.getClasses())) {
            baseResult=baseResult.fail("专业不能为空，请重新输入");
        }
        else if(StringUtils.isBlank(message.getSignDepartment())) {
            baseResult=baseResult.fail("申报部门不能为空，请重新输入");
        }
        else if(message.getGrade()==0.0) {
            baseResult=baseResult.fail("成绩不能为空，请重新输入");
        }
        else if(message.getGrade()>750.0) {
            baseResult=baseResult.fail("您输入的成绩有误，请重新输入");
        }
        else if(message.getGrade()<0.0) {
            baseResult=baseResult.fail("您输入的成绩有误，请重新输入");
        }
        else if(StringUtils.isBlank(message.getPhone())) {
            baseResult=baseResult.fail("手机号不能为空，请重新输入");
        }
        else if(!RegexpUtils.checkPhone(message.getPhone())) {
            baseResult=baseResult.fail("手机格式不正确，请重新输入");
        }
        else if(StringUtils.isBlank(message.getQq())) {
            baseResult=baseResult.fail("QQ不能为空，请重新输入");
        }
        return baseResult;
    }
}
