package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 数据字典管理表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasDict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Integer dictId;

    /**
     * 类别
     */
    private String dictType;

    /**
     * 条目
     */
    private String dictItem;

    /**
     * 值
     */
    private String dictValue;

    /**
     * 是否可编辑:1:可以 0:不可以
     */
    private Integer dictIsEditable;

}