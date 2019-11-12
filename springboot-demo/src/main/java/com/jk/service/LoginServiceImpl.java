package com.jk.service;

import com.jk.mapper.UserMapper;
import com.jk.model.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private UserMapper userMapper;


    @Override
    public JSONObject login(User user) throws JSONException {
//		如果一个方法需要返回多种数据 怎么办
        JSONObject json = new JSONObject();
        String flag = "1";//1:用户名不存在 ，2：密码错误  3：登陆成功
        List<User> userList = userMapper.queryUsersByName(user.getUsername());
//		如果根据用户名查到了数据
        if(userList != null && userList.size() >0 ){
//			默认使用第一个用户对象
            User user2 = userList.get(0);
//			如果能查到数据 则 用户名不存在的假设不成立——————————新的假设 ————密码错误
            flag = "2";
//			如果页面传过来的密码 与 数据库中查询到密码一致 则 假设不成立————————登录成功
            if(user2.getUserpwd().equals(user.getUserpwd())){
                flag = "3";
                json.put("user", user2);
            }
        }
        json.put("flag", flag);
        return json;
    }
}
