
package com.gyzjc.auth.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色关系dto
 *
 * @author makejava
 * @since 2024-09-16 23:19:47
 */
@Data
public class AuthUserRoleDTO implements Serializable {
    private static final long serialVersionUID = 475773776319609729L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private Long roleId;

}

