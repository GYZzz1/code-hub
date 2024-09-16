package com.gyzjc.auth.infra.basic.service;

import com.gyzjc.auth.infra.basic.entity.AuthRole;
import com.gyzjc.auth.infra.basic.entity.AuthRolePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 角色权限关系表(AuthRolePermission)表服务接口
 *
 * @author makejava
 * @since 2024-09-17 00:32:36
 */
public interface AuthRolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRolePermission queryById(Long id);

    /**
     * 新增数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    int insert(AuthRolePermission authRolePermission);

    /**
     * 批量新增数据
     *
     * @param authRolePermissionList 实例对象
     * @return 实例对象
     */
    int insertBatch(List<AuthRolePermission> authRolePermissionList);

    /**
     * 修改数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    AuthRolePermission update(AuthRolePermission authRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 条件查询
     * @param authRolePermission
     * @return
     */
    List<AuthRolePermission> queryByCondition(AuthRolePermission authRolePermission);

}
