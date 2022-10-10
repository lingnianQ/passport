package com.syt.passport.mapper;

import com.syt.passport.pojo.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 10:41
 */
@SpringBootTest
@Transactional
class AdminMapperTest {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    void testInsert() {
        Admin admin = Admin.builder()
                .username("Q.")
                .password("123456")
                .nickname("ling")
                .avatar("qwe")
                .phone("16605523478")
                .email("sytsnb@gmail.com")
                .description("测试账号")
                .enable(1)
                .lastLoginIp("杭州")
                .loginCount(2)
                .build();
        int rows = adminMapper.insert(admin);
        System.out.println("rows = " + rows);
    }

    @Test
    void testCountByName() {
        int countByName = adminMapper.countByUsername("Q.");
        System.out.println("countByName = " + countByName);
    }

    @Test
    void testCountByPhone() {
        int countByPhone = adminMapper.countByPhone("16605523478");
        System.out.println("countByPhone = " + countByPhone);
    }

    @Test
    void testCountByEmail() {
        int countByEmail = adminMapper.countByEmail("sytsnb@gmail.com");
        System.out.println("countByEmail = " + countByEmail);
    }
}