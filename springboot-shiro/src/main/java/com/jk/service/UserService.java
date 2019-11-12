package com.jk.service;

import com.jk.model.User;

public interface UserService {
    User queryUserByName(String username);
}
