package com.jk.service;

import com.jk.model.Role;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    HashMap<String,Object> getuserList(int page, int rows);

    List<Role> queryUserRole(Integer id);

    void saveUserRole(Integer userid, String rids);
}
