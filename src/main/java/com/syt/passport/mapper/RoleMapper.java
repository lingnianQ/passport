package com.syt.passport.mapper;

import com.syt.passport.pojo.vo.RoleListItemVO;

import java.util.List;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/10 23:03
 */
public interface RoleMapper {
    /**
     * 查询角色列表
     *
     * @return list
     */
    List<RoleListItemVO> list();
}
