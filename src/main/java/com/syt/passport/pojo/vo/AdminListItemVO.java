package com.syt.passport.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 10:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminListItemVO {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String phone;
    private String email;
    private String description;
    private Integer enable;
    private String lastLoginIp;
    private Integer loginCount;
}
