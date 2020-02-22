package com.tsw.sign.user.service;

import com.tsw.sign.commons.BaseResult;
import com.tsw.sign.commons.PageInfo;
import com.tsw.sign.message.modle.Message;
import com.tsw.sign.user.model.User;

import java.util.List;


public interface UserService {
    //User getByUserName(String username);

    List<User> selectAll();

    User login(String username, String password);
    // 更新
    void update(User user);

    User getById(Long id);

    BaseResult save(User user);
    /**分页查询
     *@Author John【chinacode@yeah.net】
     *@Date
     */
    PageInfo<User> page(int draw,int start, int length,  User user );

    int count(User user);

    void delete(Long id);

    void deleteMulit(String[] ids);

    int ishaveuser(User user);


}
