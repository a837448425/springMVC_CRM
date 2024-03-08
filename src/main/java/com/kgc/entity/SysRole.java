package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 权限表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 描述
     */
    private String roleDesc;

    /**
     * 是否可用:1可用2不可用
     */
    private Integer roleFlag;

}
