package com.jk.controller;

import com.jk.model.Menu;
import com.jk.model.User;
import com.jk.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 加载树
 */
@Controller
@RequestMapping("tree")
public class TreeController {

    @Autowired
    private TreeService treeService;

    @RequestMapping("querytree")
    @ResponseBody
    public List<Menu> querytree(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("loginUser");
//		return treeService.querytree(-1);

        return treeService.queryUserMenuTree(-1,user.getUserid());
    }
}
