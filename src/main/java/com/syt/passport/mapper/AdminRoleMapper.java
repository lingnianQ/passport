package com.syt.passport.mapper;

import com.syt.passport.pojo.entity.AdminRole;

import java.util.List;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/9 16:29
 */
public interface AdminRoleMapper {
    /**
     * 批量插入用户角色
     *
     * @param adminRoleList list
     * @return int
     */
    int insertBatch(List<AdminRole> adminRoleList);

    /**
     * 根据管理员id删除管理员与角色的关联数据
     *
     * @param adminId 管理员id
     * @return 受影响的行数
     */
    int deleteByAdminId(Long adminId);
}
