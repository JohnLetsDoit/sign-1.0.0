package com.tsw.sign.role.service.Impl;

import com.tsw.sign.commons.BaseResult;
import com.tsw.sign.role.dao.RoleMapper;
import com.tsw.sign.role.model.Role;
import com.tsw.sign.role.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }
    @Override
    public BaseResult save(Role role) {
        BaseResult baseResult=checkRole(role);
        if (baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
                roleMapper.update(role);
                baseResult.setMessage("改变角色信息成功");
            }else {
            baseResult.setMessage("改变角色信息失败");
        }
        return baseResult;
    }

    @Override
    public void update(Role role) {
        roleMapper.update(role);
    }

    @Override
    public Role getById(Long id) {
        return roleMapper.getById(id);
    }

    /**用户信息有效性验证
     *@Author John【chinacode@yeah.net】
     *@Date
     */
    private BaseResult checkRole(Role role){
//        默认是成功的
        BaseResult baseResult=BaseResult.success();

        if(StringUtils.isBlank(role.getDepartment())) {
            baseResult=baseResult.fail("角色不能为空，请重新输入");
        }
        return baseResult;
    }
}
