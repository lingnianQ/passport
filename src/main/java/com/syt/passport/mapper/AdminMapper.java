package com.syt.passport.mapper;

import com.syt.passport.pojo.entity.Admin;
import com.syt.passport.pojo.vo.AdminListItemVO;
import com.syt.passport.pojo.vo.AdminLoginInfoVO;
import com.syt.passport.pojo.vo.AdminStandardVO;

import java.util.List;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 10:29
 */
public interface AdminMapper {
    /**
     * 插入
     * @param admin
     * @return
     */
    int insert(Admin admin);

    /**
     * 通过id删除管理员
     * @param id 管理员id
     * @return 影响的行数
     */
    int deleteById(Long id);

    /**
     * 通过id更新管理员
     * @param admin 管理员
     * @return int
     */
    int updateById(Admin admin);

    /**
     * 通过用户名查找
     * @param username
     * @return
     */
    int countByUsername(String username);

    /**
     * 通过手机号查找数量
     * @param phone
     * @return
     */
    int countByPhone(String phone);

    /**
     * 通过用户名查找
     * @param email
     * @return int
     */
    int countByEmail(String email);

    /**
     * 获取管理员数据
     * @param id
     * @return AdminStandardVO
     */
    AdminStandardVO getStandardById(Long id);

    /**
     * 查询管理员列表
     * @return List<AdminListItemVO>
     */
    List<AdminListItemVO> list();

    /**
     * 管理员登录
     * @param name
     * @return
     */
    AdminLoginInfoVO getLoginInfoByUsername(String name);
}
