package com.syt.passport.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * AddNew
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 11:35
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminAddNewDTO implements Serializable {
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String phone;
    private String email;
    private String description;
    private Integer enable;
}
