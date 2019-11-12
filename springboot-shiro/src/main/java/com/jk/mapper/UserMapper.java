package com.jk.mapper;

import com.jk.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User queryUserByName(@Param("username") String username);
}
