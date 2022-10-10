package com.syt.passport.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色的列表项VO类
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/10 23:03
 */
@Data
public class RoleListItemVO implements Serializable {

    /**
     * 数据id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 自定义排序序号
     */
    private Integer sort;

}