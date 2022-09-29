package com.syt.passport.mapper;

import com.syt.passport.pojo.entity.Admin;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 10:29
 */
public interface AdminMapper {
    /**
     * 插入
     *
     * @param admin
     * @return
     */
    int insert(Admin admin);

    /**
     * 通过用户名查找
     *
     * @param username
     * @return
     */
    int countByUsername(String username);

    /**
     * 通过手机号查找数量
     *
     * @param phone
     * @return
     */
    int countByPhone(String phone);
}
