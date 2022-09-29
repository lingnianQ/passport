package com.syt.passport.pojo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author sytsn
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {

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
  private LocalDateTime gmtLastLogin;
  private LocalDateTime gmtCreate;
  private LocalDateTime gmtModified;

}
