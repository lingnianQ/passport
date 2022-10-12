package com.syt.passport.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 10:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginInfoVO implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Integer enable;
}
