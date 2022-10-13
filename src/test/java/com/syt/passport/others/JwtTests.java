package com.syt.passport.others;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTests {

    String secretKey = "kns439a}fdLK34jsmfd{MF5-8DJSsLKhJNFDSjn";

    @Test
    public void testGenerate() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 9527);
        claims.put("username", "liucangsong");
        claims.put("email", "liucangsong@163.com");

        Date expirationDate = new Date(System.currentTimeMillis() + 10 * 60 * 60 * 24 * 1000);
        System.out.println("过期时间：" + expirationDate);

        String jwt = Jwts.builder()
                // Header
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                // Payload
                .setClaims(claims)
                .setExpiration(expirationDate)
                // Signature
                .signWith(SignatureAlgorithm.HS256, secretKey)
                // 整合
                .compact();
        System.out.println(jwt);

        // 过期时间：Wed Oct 12 17:14:41 CST 2022
        // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTUyNywiZXhwIjoxNjY1NTY2MDgxLCJlbWFpbCI6ImxpdWNhbmdzb25nQDE2My5jb20iLCJ1c2VybmFtZSI6ImxpdWNhbmdzb25nIn0.zimcqHJ9w9i1ut-uQtJASIfwz5_LzrUvU_l_SHq_crA
    }

    @Test
    public void testParse() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTUyNywiZXhwIjoxNjY1NTcyNjkxLCJlbWFpbCI6ImxpdWNhbmdzb25nQDE2My5jb20iLCJ1c2VybmFtZSI6ImxpdWNhbmdzb25nIn0.c3SZCEvNdMcId6S3prY9doAfOJwtTq9jfioWnOR-9bQ";
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        Integer id = claims.get("id", Integer.class);
        System.out.println("id=" + id);
        String username = claims.get("username", String.class);
        System.out.println("username=" + username);
        String email = claims.get("email", String.class);
        System.out.println("email=" + email);
        String phone = claims.get("phone", String.class);
        System.out.println("phone=" + phone);
    }

}
