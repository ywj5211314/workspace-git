package com.jk.service;

import com.jk.model.Menu;
import com.jk.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getroleList();

    List<Menu> queryRoleMenu(int pid, int rid);

    void saveRoleMenu(int rid, String meid);
}
