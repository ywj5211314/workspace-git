package com.jk.mapper;

import com.jk.model.Role;
import com.jk.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from t_user where username=#{username}")
    List<User> queryUsersByName(@Param("username")  String username);

    long querytotal();

    List<User> getuserList(@Param("start") int start, @Param("rows") int rows);

    List<Role> queryRoleAll();

    List<Integer> queryRoleByUid(@Param("uid")Integer id);


    void deleteUserRoleByUid(Integer userid);

    void saveUserRole(@Param("userid")Integer userid, @Param("rids")String[] ridArr);
}
