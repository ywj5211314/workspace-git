package com.jk.controller;

import com.jk.model.User;
import com.jk.service.LoginService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService LoginService;

    //登录
    @RequestMapping("login")
    @ResponseBody
    public String login(User user, String code, HttpServletRequest request) throws JSONException {
        String flag = "";
            JSONObject json = LoginService.login(user);
            flag = json.getString("flag");
//			如果标识为3 证明登录成功 需要将用户信息存储到session中
            if(flag.equals("3")){
                User users = (User) json.get("user");
                request.getSession().setAttribute("loginUser", users);
        }
        return flag;
    }
    @RequestMapping("tologin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("toindex")
    public String toindex(){
        return "Tree";
    }


}
