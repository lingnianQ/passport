package com.syt.passport.others;

import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;


/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/11 1:14
 */
public class Md5Tests {
    @Test
    public void testMd5() {
        String salt = "n(N(wWs/5*6WPzM+n(N(wWs/5*6WPzM+n(N(wWs/5*6WPzM+";

        String rawPassword = "123456";
        System.out.println("rawPassword = " + rawPassword);

        String encodePassword = DigestUtils
                .md5DigestAsHex((rawPassword + salt).getBytes());
        System.out.println("encodePassword = " + encodePassword);
        
    }
}
