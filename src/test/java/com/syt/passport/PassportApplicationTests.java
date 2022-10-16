package com.syt.passport;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class PassportApplicationTests {

    @Value("${passport.jwt.secret-key}")
    private String secretKey;

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {

        System.out.println("secretKey = " + secretKey);

        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
        System.out.println("数据库连接成功");
    }

}
