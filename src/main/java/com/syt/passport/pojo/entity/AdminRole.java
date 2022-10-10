package com.syt.passport.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户角色
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/9 16:27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRole {

    /**
     * 记录id
     */
    private Long id;

    /**
     * 管理员id
     */
    private Long adminId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 数据修改时间
     */
    private LocalDateTime gmtModified;
}
