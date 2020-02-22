package com.tsw.sign.user.service.impl;

import com.tsw.sign.commons.BaseResult;
import com.tsw.sign.commons.PageInfo;
import com.tsw.sign.user.dao.UserMapper;
import com.tsw.sign.user.model.User;
import com.tsw.sign.user.service.UserService;
import com.tsw.sign.utils.RegexpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public int ishaveuser(User user) {
        return userMapper.ishaveuser(user);
    }

    @Override
    public int count(User user) {
        return userMapper.count(user);
    }

    @Override
    public PageInfo<User> page(int draw,int start, int length,  User user) {
        int count=userMapper.count(user);
        Map<String,Object> params=new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("draw",draw);
        params.put("user",user);

        PageInfo<User> pageInfo=new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setRecordsTotal(count);
        pageInfo.setData(userMapper.page(params));
        return pageInfo;
    }

    @Override
    public void deleteMulit(String[] ids) {
        userMapper.deleteMulit(ids);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }

    @Override
    public BaseResult save(User user) {
        BaseResult baseResult=checkUser(user);
        if (baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
                if (user.getId() == null) {
                    user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                    user.setCreatetime(new Date());
                    userMapper.insert(user);
                }
                else {
                    user.setLastlogintime(new Date());
                    user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                    userMapper.update(user);


                }
                baseResult.setMessage("保存用户信息成功");
        }
        return baseResult;
    }

    @Override
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.getByUsername(username);
        if (user != null) {
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

            if (md5Password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
    /**用户信息有效性验证
     *@Author John【chinacode@yeah.net】
     *@Date
     */
    private BaseResult checkUser(User user){
//        默认是成功的
        BaseResult baseResult=BaseResult.success();
        int counts=userMapper.ishaveuser(user);
        if (counts>0&&user.getId()==null){
            baseResult.setStatus(500);
            baseResult.setMessage("该用户已存在！");
        }else if(StringUtils.isBlank(user.getDepartment())) {
            baseResult=baseResult.fail("部门不能为空，请重新输入");
        }
        else if(StringUtils.isBlank(user.getPassword())) {
            baseResult=baseResult.fail("密码不能为空，请重新输入");
        }
        else if(StringUtils.isBlank(user.getUsername())) {
            baseResult=baseResult.fail("手机不能为空，请重新输入");
        }
        else if(!RegexpUtils.checkPhone(user.getPhone())) {
            baseResult=baseResult.fail("手机格式不正确，请重新输入");
        }
        else if(StringUtils.isBlank(user.getUsername())) {
            baseResult=baseResult.fail("姓名不能为空，请重新输入");
        }
        return baseResult;
    }

}