package com.syt.passport.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 角色接口测试类
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/11 1:12
 */
@Slf4j
@SpringBootTest
class IRoleServiceTest {
    @Autowired
    private IRoleService roleService;

    @Test
    void testList() {

        roleService.list().forEach(System.out::println);
    }
}