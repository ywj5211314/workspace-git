package com.jk.service;

import com.jk.model.User;
import org.json.JSONException;
import org.json.JSONObject;

public interface LoginService {
    JSONObject login(User user) throws JSONException;
}
