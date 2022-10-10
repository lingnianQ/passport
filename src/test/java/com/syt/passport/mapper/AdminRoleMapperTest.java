package com.syt.passport.mapper;

import com.syt.passport.pojo.entity.AdminRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/9 16:35
 */
@Slf4j
@SpringBootTest
class AdminRoleMapperTest {
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Test
    @Transactional
    void insertBatch() {
        List<AdminRole> adminRoleList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AdminRole adminRole = AdminRole.builder()
                    .adminId(1L + i)
                    .roleId(1L + i)
                    .build();
            adminRoleList.add(adminRole);
        }
        int rows = adminRoleMapper.insertBatch(adminRoleList);
        log.debug("rows = {}", rows);
    }
}