package com.tsw.sign.user.dao;

import com.tsw.sign.message.modle.Message;
import com.tsw.sign.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    //通过用户名获取用户
    User getByUsername(String username);
    //
    List<User> selectAll();
    //新增用户
    void  insert(User user);

    User getById(Long id);

    void update(User user);

    /**分页查询 需要两个参数 starts length
     *@Author John【chinacode@yeah.net】
     *@Date
     */
    List<User> page(Map<String,Object> params);

    int count(User user);

    void delete(Long id);

    void deleteMulit(String[] ids);

    int ishaveuser(User user);

}
