package com.syt.passport.service.impl;

import com.syt.passport.mapper.RoleMapper;
import com.syt.passport.pojo.vo.RoleListItemVO;
import com.syt.passport.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理角色数据的业务实现类
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/10 23:02
 */
@Slf4j
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    public RoleServiceImpl() {
        log.debug("创建业务对象：RoleServiceImpl");
    }

    @Override
    public List<RoleListItemVO> list() {
        return roleMapper.list();
    }
}
