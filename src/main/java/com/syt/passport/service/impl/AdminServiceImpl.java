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
import java.util.Iterator;
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
        // 调用adminRoleMapper的insertBatch()方法插入关联数据
        Long[] roleIds = adminAddNewDTO.getRoleIds();
        for (Long roleId : roleIds) {
            if (roleId == 1) {
                String message = "添加失败,非法访问";
                log.debug(message);
                throw new ServiceException(ServiceCode.ERR_INSERT, message);
            }
        }

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
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        log.debug("即将检查手机号码是否被占用……");
        if (adminMapper.countByPhone(adminAddNewDTO.getPhone()) != 0) {
            String message = "该手机号已经被注册";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        log.debug("即将检查电子邮箱是否被占用……");
        if (adminMapper.countByEmail(adminAddNewDTO.getEmail()) != 0) {
            String message = "该电子邮箱已经被注册";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        Admin admin = new Admin();
        BeanUtils.copyProperties(adminAddNewDTO, admin);
        // TODO 从Admin对象中取出密码，进行加密处理，并将密文封装回Admin对象中
        admin.setLoginCount(0);
        log.debug("开始插入管理员: {}", admin);
        int rows = adminMapper.insert(admin);
        if (rows != 1) {
            String message = "添加管理员失败，服务器忙，请稍后再次尝试！ addNew-1";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
        log.debug("管理员插入成功");

        log.debug("开始插入角色");

        if (roleIds.length < 1) {
            String message = "角色id不存在--addNew";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }

        List<AdminRole> adminRoleList = new ArrayList<>();
        for (int i = 0; i < roleIds.length; i++) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(admin.getId());
            adminRole.setRoleId(roleIds[i]);
            adminRoleList.add(adminRole);
        }
        rows = adminRoleMapper.insertBatch(adminRoleList);
        if (rows != roleIds.length) {
            String message = "添加管理员失败，服务器忙，请稍后再次尝试！ addNew-2";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
        log.debug("管理员角色插入成功");
    }

    @Override
    public void deleteById(Long id) {
        if (id == 1) {
            String message = "删除失败,用户id不存在";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
        log.debug("开始删除管理员数据: {}", id);
        AdminStandardVO adminStandardVO = adminMapper.getStandardById(id);

        if (adminStandardVO == null) {
            String message = "删除失败,管理员id不存在";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
        log.debug("开始删除管理员数据: {}", id);
        int rows = adminMapper.deleteById(id);
        if (rows != 1) {
            String message = "删除管理员失败，服务器忙，请稍后再次尝试！-1";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }

        rows = adminRoleMapper.deleteByAdminId(id);
        if (rows < 1) {
            String message = "删除管理员失败，服务器忙，请稍后再次尝试！-2";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
        log.debug("删除管理员成功");
    }

    @Override
    public void setEnable(Long id) {
        updateEnableById(id, 1);
    }

    @Override
    public void setDisable(Long id) {
        updateEnableById(id, 0);
    }

    @Override
    public List<AdminListItemVO> list() {
        log.debug("开始处理【查询管理员列表】的业务");
        List<AdminListItemVO> adminList = adminMapper.list();
        Iterator<AdminListItemVO> iterator = adminList.iterator();
        while (iterator.hasNext()) {
            AdminListItemVO item = iterator.next();
            if (item.getId() == 1) {
                iterator.remove();
                break;
            }
        }
        return adminList;
    }

    private void updateEnableById(Long id, Integer enable) {
        if (id == 1) {
            String message = "用户id不存在";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
        String[] tips = {"禁用", "启用"};
        log.debug("开始处理【{}管理员】的业务，参数：{}", tips[enable], id);
        // 检查尝试访问的数据是否存在
        AdminStandardVO adminStandardVO = adminMapper.getStandardById(id);
        if (adminStandardVO == null) {
            String message = tips[enable] + "管理员失败，尝试访问的数据不存在！";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 判断状态是否冲突（当前已经是目标状态）
        if (adminStandardVO.getEnable().equals(enable)) {
            String message = tips[enable] + "管理员失败，管理员账号已经处于" + tips[enable] + "状态！";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 准备执行更新
        Admin admin = new Admin();
        admin.setId(id);
        admin.setEnable(enable);
        int rows = adminMapper.updateById(admin);
        if (rows != 1) {
            String message = tips[enable] + "管理员失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }
}
