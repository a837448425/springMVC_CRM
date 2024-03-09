package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 权限表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRigth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Integer rightId;

    /**
     * 上级栏目编号
     */
    private String rightParentId;

    /**
     * 权限类型:1:菜单 2:权限
     */
    private Integer rightType;

    /**
     * 权限名称
     */
    private String rightText;

    /**
     * 权限地址
     */
    private String rightUrl;

}
