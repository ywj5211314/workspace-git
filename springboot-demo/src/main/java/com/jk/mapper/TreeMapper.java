package com.jk.mapper;

import com.jk.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreeMapper {
    List<Menu> queryUserMenuTree(@Param("pid")int pid, @Param("userid")Integer userid);

    List<Menu> queryMenusByPid(@Param("pid") int pid);
}
