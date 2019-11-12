package com.jk.service;

import com.jk.mapper.UserMapper;
import com.jk.model.Role;
import com.jk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public HashMap<String,Object> getuserList(int page, int rows){
        long total = userMapper.querytotal();
        int start = (page-1)*rows;
        List<User> us = userMapper.getuserList(start,rows);
        /*JSONObject json = new JSONObject();
        json.put("total",total);
        json.put("rows", us);*/
        HashMap<String,Object> map =  new HashMap<String,Object>();
        map.put("total",total);
        map.put("rows",us);
        return map;
    }

    @Override
    public List<Role> queryUserRole(Integer id) {
//      1.查询所有角色
        List<Role> roles1 = userMapper.queryRoleAll();
//		2.查询该用户已有角色id集合
        List<Integer> roles2 = userMapper.queryRoleByUid(id);
//		3.双重for循环嵌套 比较
        for (int i = 0; i < roles1.size(); i++) {
            for (int j = 0; j < roles2.size(); j++) {
                if(roles1.get(i).getRoleid() == roles2.get(j)){
//					4.如果两个角色id相同 则设置业务字段 status为true
                    roles1.get(i).setStatus("checked");
                }
            }
        }
        return roles1;
    }

    @Override
    public void saveUserRole(Integer userid, String rids) {
//      1.删除中间表中该用户的所有数据
        userMapper.deleteUserRoleByUid(userid);
//		2.添加给该用户设置的角色
        String[] ridArr = rids.split(",");
        userMapper.saveUserRole(userid,ridArr);
    }
}
