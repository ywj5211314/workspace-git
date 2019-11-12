package com.jk.service;

import com.jk.mapper.TreeMapper;
import com.jk.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeMapper treeMapper;

    @Override
    public List<Menu> queryUserMenuTree(int pid, Integer userid) {
        //	根据pid和用户id查询用户的权限列表
        List<Menu> menus = treeMapper.queryUserMenuTree(pid,userid);
        if(menus !=null && menus.size()>0){
            for (int i = 0; i < menus.size(); i++) {
                List<Menu> menus2 = queryUserMenuTree(menus.get(i).getId(),userid);
                menus.get(i).setChildren(menus2);
            }
        }
        return menus;
    }

    @Override
    public List<Menu> querytree(int pid) {
        List<Menu> menus = treeMapper.queryMenusByPid(pid);
        if(menus !=null && menus.size()>0){
            for (int i = 0; i < menus.size(); i++) {
                List<Menu> menus2 = querytree(menus.get(i).getId());
                menus.get(i).setChildren(menus2);
            }
        }
        return menus;
    }
}
