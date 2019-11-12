package com.jk.mapper;

import com.jk.model.Menu;
import com.jk.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<Role> getroleList();

    List<Menu> queryMenuTree(@Param("pid")int pid);

    List<Integer> queryMenuIdByRid(@Param("rid")int rid);

    void deleteMenuByRid(int rid);

    void saveRoleMenu(@Param("rid")int rid, @Param("mids")String[] split);
}
