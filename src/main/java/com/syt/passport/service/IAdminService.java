package com.syt.passport.service;

import com.syt.passport.pojo.dto.AdminAddNewDTO;
import com.syt.passport.pojo.vo.AdminListItemVO;

import java.util.List;

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

    /**
     * 通过id删除
     *
     * @param id 管理员id
     */
    void deleteById(Long id);

    /**
     * 查询管理员列表
     *
     * @return List<AdminListItemVO>
     */
    List<AdminListItemVO> list();

}
