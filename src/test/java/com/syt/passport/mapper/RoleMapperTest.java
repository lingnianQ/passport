package com.syt.passport.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/11 1:20
 */
@Slf4j
@SpringBootTest
public class RoleMapperTest {

    @Autowired
    private RoleMapper rolemapper;

    @Test
    void testList() {
        rolemapper.list().forEach(System.out::println);

    }

}