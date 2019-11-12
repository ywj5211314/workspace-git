package com.jk.service;

import com.jk.model.Menu;

import java.util.List;

public interface TreeService {
    List<Menu> queryUserMenuTree(int pid, Integer userid);

    List<Menu> querytree(int pid);
}
