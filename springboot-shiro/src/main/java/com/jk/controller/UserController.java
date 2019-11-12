package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("toAdd")
 // @RequiresPermissions("user:toAdd")
 // @RequiresRoles("role1")
    public String toAdd(){
        return "addUser";
    }

    @RequestMapping("403")
    public String nopower(){
        return "403";
    }
}
