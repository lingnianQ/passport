package com.syt.passport.service;

import com.syt.passport.pojo.dto.AdminAddNewDTO;
import com.syt.passport.pojo.dto.AdminLoginDTO;
import com.syt.passport.pojo.entity.AdminRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 11:49
 */
@Slf4j
@SpringBootTest
@Transactional
class IAdminServiceTest {

    @Autowired
    private IAdminService adminService;

    @Test
    void testLogin() {
        log.debug("testLogin---测试开始");
        {
            AdminLoginDTO adminLoginDTO = AdminLoginDTO.builder()
                    .username("root")
                    .password("123456")
                    .build();
            String jwt = adminService.login(adminLoginDTO);
//            System.out.println("jwt = " + jwt);
        }
        log.debug("testLogin---测试结束");
    }

    @Test
    void testAddNew() {
        Long[] roleIds = {12L, 54L, 44L};
        AdminAddNewDTO adminAddNewDTO = AdminAddNewDTO.builder()
                .username("Q.1")
                .password("123456")
                .phone("16605523666")
                .email("1232@qq.com")
                .roleIds(roleIds)
                .build();
        adminService.addNew(adminAddNewDTO);
    }

    @Test
    void testList() {
        adminService.list().forEach(System.out::println);
    }

    @Test
    void deleteById() {
        adminService.deleteById(35L);
    }


}