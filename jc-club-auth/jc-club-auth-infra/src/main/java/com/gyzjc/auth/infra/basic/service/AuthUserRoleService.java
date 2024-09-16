package com.gyzjc.auth.infra.basic.service;

import com.gyzjc.auth.infra.basic.entity.AuthUserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 用户角色关系表(AuthUserRole)表服务接口
 *
 * @author makejava
 * @since 2024-09-16 23:19:47
 */
public interface AuthUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUserRole queryById(Long id);

    /**
     * 新增数据
     *
     * @param authUserRole 实例对象
     * @return 实例对象
     */
    AuthUserRole insert(AuthUserRole authUserRole);

    /**
     * 修改数据
     *
     * @param authUserRole 实例对象
     * @return 实例对象
     */
    AuthUserRole update(AuthUserRole authUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
