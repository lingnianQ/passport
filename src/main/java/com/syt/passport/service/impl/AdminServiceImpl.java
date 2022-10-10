package com.syt.passport.service.impl;

import com.syt.passport.ex.ServiceException;
import com.syt.passport.mapper.AdminMapper;
import com.syt.passport.mapper.AdminRoleMapper;
import com.syt.passport.pojo.dto.AdminAddNewDTO;
import com.syt.passport.pojo.entity.Admin;
import com.syt.passport.pojo.entity.AdminRole;
import com.syt.passport.pojo.vo.AdminListItemVO;
import com.syt.passport.pojo.vo.AdminStandardVO;
import com.syt.passport.service.IAdminService;
import com.syt.passport.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 10:31
 */
@Slf4j
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;


    @Override
    public void addNew(AdminAddNewDTO adminAddNewDTO) {
        System.out.println("adminAddNewDTO = " + adminAddNewDTO);

        if (!StringUtils.hasText(StringUtils.trimWhitespace(adminAddNewDTO.getUsername()))) {
            String message = "用户名不能为空";
            log.info(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
        if (StringUtils.containsWhitespace(adminAddNewDTO.getUsername())) {
            String message = "用户名不能包含空白字符";
            log.info(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
        //------------------------------------------------------------
        log.debug("即将检查用户名是否被占用……");
        if (adminMapper.countByUsername(adminAddNewDTO.getUsername()) != 0) {
            String message = "用户已存在";
            log.info(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        log.debug("即将检查手机号码是否被占用……");
        if (adminMapper.countByPhone(adminAddNewDTO.getPhone()) != 0) {
            String message = "该手机号已经被注册";
            log.info(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        log.debug("即将检查电子邮箱是否被占用……");
        if (adminMapper.countByEmail(adminAddNewDTO.getEmail()) != 0) {
            String message = "该电子邮箱已经被注册";
            log.info(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        Admin admin = new Admin();
        BeanUtils.copyProperties(adminAddNewDTO, admin);
        admin.setLoginCount(0);
        log.info("开始插入用户");
        adminMapper.insert(admin);
        log.info("用户插入成功");

        log.debug("开始插入角色");
// 调用adminRoleMapper的insertBatch()方法插入关联数据
        Long[] roleIds = adminAddNewDTO.getRoleIds();
        List<AdminRole> adminRoleList = new ArrayList<>();
        for (int i = 0; i < roleIds.length; i++) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(admin.getId());
            adminRole.setRoleId(roleIds[i]);
            adminRoleList.add(adminRole);
        }
        adminRoleMapper.insertBatch(adminRoleList);
        log.debug("管理员角色插入成功");


    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始删除管理员数据: {}", id);
        AdminStandardVO adminStandardVO = adminMapper.getStandardById(id);

        if (adminStandardVO == null) {
            String message = "删除失败,管理员id不存在";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
        log.debug("开始删除管理员数据: {}", id);
        adminMapper.deleteById(id);
        log.debug("删除管理员成功");
    }

    @Override
    public List<AdminListItemVO> list() {
        return adminMapper.list();
    }
}
