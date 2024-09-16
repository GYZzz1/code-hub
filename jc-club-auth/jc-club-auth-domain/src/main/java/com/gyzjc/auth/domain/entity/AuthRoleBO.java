package com.gyzjc.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色BO
 *
 * @author makejava
 * @since 2024-09-16 22:34:09
 */
@Data
public class AuthRoleBO implements Serializable {
    private static final long serialVersionUID = -42915915168269288L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 唯一标识
     */
    private String roleKey;

}

