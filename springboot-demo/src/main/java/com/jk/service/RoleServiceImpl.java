package com.jk.service;

import com.jk.mapper.RoleMapper;
import com.jk.model.Menu;
import com.jk.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getroleList() {
        return roleMapper.getroleList();
    }

    @Override
    public List<Menu> queryRoleMenu(int pid, int rid) {
//      1.根据pid 查询所有子节点列表
        List<Menu> menus1 = roleMapper.queryMenuTree(pid);
//			查询该角色已有的所有权限id集合----------查询角色权限中间表
        List<Integer> roleMenus = roleMapper.queryMenuIdByRid(rid);
//			操作复选框是否被选中 ----开始
			/*for (int i = 0; i < menus1.size(); i++) {
				List<Menu> list = queryRoleMenu(menus1.get(i).getId(),rid);

				for (int j = 0; j < roleMenus.size(); j++) {
					if(list.size() <= 0 && menus1.get(i).getId() == roleMenus.get(j)){
//						当角色已拥有的权限id 在 权限集合中被找到 则设置业务字段——————checked
						menus1.get(i).setChecked(true);
					}
				}
			}*/
//			操作复选框是否被选中 ----结束

//			2.判断该列表不为空 有值
        if(menus1 != null && menus1.size() >0){
//				3.循环该列表
            for (int i = 0; i < menus1.size(); i++) {
//					4.在循环体内 将当前元素的id 作为pid 调用当前方法本身 进行递归
                List<Menu> menus2 = queryRoleMenu(menus1.get(i).getId(),rid);
//					5.如果能够查到子节点列表 则将该列表放到 children属性中
                menus1.get(i).setChildren(menus2);

                for (int j = 0; j < roleMenus.size(); j++) {
//						如果根据当前节点id 没有查到子节点列表 说明当前节点对象是最底层节点
//						并且如果该节点id与角色已拥有的权限id相同则设置该节点对象的checked=true
                    if(menus2.size() <= 0 && menus1.get(i).getId() == roleMenus.get(j)){
//							当角色已拥有的权限id 在 权限集合中被找到 则设置业务字段——————checked
                        menus1.get(i).setChecked(true);
                    }
                }
            }


        }

        return menus1;
    }

    @Override
    public void saveRoleMenu(int rid, String meid) {
//      System.out.println(rid+"============"+meid+"===========");
//		1.删除中间表数据
        roleMapper.deleteMenuByRid(rid);
//		2.重新添加中间表
        roleMapper.saveRoleMenu(rid,meid.split(","));

    }
}
