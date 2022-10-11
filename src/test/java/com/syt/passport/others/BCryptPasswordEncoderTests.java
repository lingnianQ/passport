package com.syt.passport.others;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author sytsnb@gmail.com
 * @Date 2022 2022/10/11 14:12
 */

public class BCryptPasswordEncoderTests {
    @Test
    void testEncode() {
        String rawPassword = "123456";
        for (int i = 0; i < 10; i++) {
            String encodePassword = new BCryptPasswordEncoder()
                    .encode(rawPassword);
            System.out.println("encodePassword = " + encodePassword);
        }
//        $2a$10$7wgJcKYBcjsp9s25r7/KhecWyBa0WrRKppbRueHZgLRL6l2aUx1je
//        $2a$10$PQ7bBC/bM6/DpNE4RxAjCODlqT4V7Lt4pYtInfKh3P2ua.IVjSOOG
//        $2a$10$hTGSYoF0TnaoS5Xqomds9eFXlmAehDKnwd44SfyBZFgr8VVDeTgA6
//        $2a$10$61VBsDnCRk3p/dZdFfqT3O4m2PuNsnRhpuAupZNL7nzKhlvFwp6P2
//        $2a$10$rdI69nC6fezUJdf73OEs8eQHjZaSMSId3ycbgLg/7UoD1c1OdYtJC
//        $2a$10$Xtx387gyE64vEc1yBhGc1eSovWXK0RC42vH4ZVaQdhTDDDMzVK04y
//        $2a$10$Xfwcuw7xbzE.uzdr2sLNkOh/D8WrK1gdaj2vn4Oh7bbkUOwNBUbYe
//        $2a$10$CulHfW7RQFamQRyh20.0suJ09wszAZ2Do6Dbd5nlspD2N/K8Gnskq
//        $2a$10$PUd80akGRmpNiB.mjF3ucez1hbYZXEq.jUl7Hd.m/5P/XjW2gZEaS
//        $2a$10$114vVrY9DQXkXLkthaELF.NahSfed8pcIPezrCwpZ8frOxUd7I5wK
    }
    
    @Test
    void testMatches(){
        String rawPassword = "123456";
        String encodePassword = "$2a$10$114vVrY9DQXkXLkthaELF.NahSfed8pcIPezrCwpZ8frOxUd7I5wK";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(rawPassword, encodePassword);
        System.out.println("matches = " + matches);
    }
}
