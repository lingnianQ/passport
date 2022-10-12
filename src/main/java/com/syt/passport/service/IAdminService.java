package com.syt.passport.service;

import com.syt.passport.pojo.dto.AdminAddNewDTO;
import com.syt.passport.pojo.dto.AdminLoginDTO;
import com.syt.passport.pojo.vo.AdminListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 管理员数据业务接口
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 10:30
 */
@Transactional
public interface IAdminService {

    /**
     * 管理员登录
     *
     * @param adminLoginDTO 封装了管理员的登录信息的对象
     */
    void login(AdminLoginDTO adminLoginDTO);

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
     * 启用管理员
     *
     * @param id 管理员的id
     */
    void setEnable(Long id);

    /**
     * 禁用管理员
     *
     * @param id 管理员的id
     */
    void setDisable(Long id);

    /**
     * 查询管理员列表
     *
     * @return List<AdminListItemVO>
     */
    List<AdminListItemVO> list();

}
