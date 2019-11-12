package com.jk.controller;

import com.jk.model.Role;
import com.jk.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getuserList")
    @ResponseBody
    public HashMap<String,Object> getuserList(int page, int rows){

        return userService.getuserList(page,rows);
    }

    @RequestMapping("touser")
    public String touser(){
        return "User";
    }

    @RequestMapping("togetUserRole")
    public String togetUserRole(Integer id, Model model, HttpServletRequest request){
        request.getSession().setAttribute("id", id);
        List<Role> rolelist = userService.queryUserRole(id);
        model.addAttribute("roles", rolelist);
        return "UserRole";

    }

    @RequestMapping("saveUserRole")
    @ResponseBody
    public String saveUserRole(Integer userid,String rids){
        userService.saveUserRole(userid,rids);
        return "suc";
    }
}
