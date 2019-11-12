package com.jk.controller;

import com.jk.model.Menu;
import com.jk.model.Role;
import com.jk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("getroleList")
    @ResponseBody
    public List<Role> getroleList(){
        return roleService.getroleList();

    }
    @RequestMapping("qu1")
    public String qu1(){
        return "RoleShow";
    }

    @RequestMapping("togetRoleMenu")
    public String togetRoleMenu(Integer rid, HttpServletRequest request){
       /* model.addAttribute("rid", rid);*/
        request.getSession().setAttribute("rid", rid);
        return "RoleMenu";
    }

    @RequestMapping("queryRoleMenu")
    @ResponseBody
    public List<Menu> queryRoleMenu(HttpServletRequest request){
        Integer rid = (Integer) request.getSession().getAttribute("rid");
        return roleService.queryRoleMenu(-1,rid);

    }

    @RequestMapping("saveRoleMenu")
    @ResponseBody
    public String saveRoleMenu(int rid,String meid){
        roleService.saveRoleMenu(rid,meid);
        return "suc";
    }
}
