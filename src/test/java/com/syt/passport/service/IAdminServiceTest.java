package com.syt.passport.service;

import com.syt.passport.pojo.dto.AdminAddNewDTO;
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
    void testAddNew() {
        AdminAddNewDTO adminAddNewDTO = AdminAddNewDTO.builder()
                .username("Q.1")
                .phone("16605523666")
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