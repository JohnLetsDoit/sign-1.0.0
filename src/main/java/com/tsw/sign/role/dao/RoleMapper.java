package com.tsw.sign.role.dao;

import com.tsw.sign.role.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    List<Role> selectAll();
    void update(Role role);
    Role getById(Long id);
}
