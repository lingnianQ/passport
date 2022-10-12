package com.syt.passport.mapper;

import com.syt.passport.pojo.entity.Admin;
import com.syt.passport.pojo.vo.AdminLoginInfoVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    void testDeleteById() {
        Long id = 11L;
        int rows = adminMapper.deleteById(id);
        System.out.println("删除数据完成，受影响的行数=" + rows);
    }

    @Test
    void testUpdateById() {
        Admin data = new Admin();
        data.setId(1L);
        data.setUsername("新的测试数据名称");

        int rows = adminMapper.updateById(data);
        System.out.println("修改数据完成，受影响的行数=" + rows);
    }

    @Test
    void testCountByUsername() {
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

    @Test
    void testGetStandardById() {
        Long id = 1L;
        Object result = adminMapper.getStandardById(id);
        System.out.println("根据id=" + id + "查询标准信息完成，结果=" + result);
    }

    @Test
    void testList() {
        List<?> list = adminMapper.list();
        System.out.println("查询列表完成，列表中的数据的数量=" + list.size());
        for (Object item : list) {
            System.out.println(item);
        }
    }

    @Test
    void getLoginInfoByUsername() {
        AdminLoginInfoVO root = adminMapper.getLoginInfoByUsername("root");
        System.out.println("root = " + root);
    }
}