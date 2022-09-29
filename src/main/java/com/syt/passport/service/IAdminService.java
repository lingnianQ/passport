package com.syt.passport.service;

import com.syt.passport.pojo.dto.AdminAddNewDTO;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 10:30
 */
public interface IAdminService {
    /**
     * 添加新用户
     *
     * @param adminAddNewDTO
     */
    void addNew(AdminAddNewDTO adminAddNewDTO);
}
