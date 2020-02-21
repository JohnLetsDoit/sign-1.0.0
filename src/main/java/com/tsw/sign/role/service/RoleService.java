package com.tsw.sign.role.service;

import com.tsw.sign.commons.BaseResult;
import com.tsw.sign.role.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> selectAll();
    BaseResult save(Role role);
    void update(Role role);
    Role getById(Long id);


}
