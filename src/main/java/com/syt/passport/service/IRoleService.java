package com.syt.passport.service;

import com.syt.passport.pojo.vo.RoleListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 处理角色的业务接口
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/10 23:01
 */
@Transactional
public interface IRoleService {
    /**
     * 查询角色列表
     *
     * @return list
     */
    List<RoleListItemVO> list();
}
