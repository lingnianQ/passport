package com.syt.passport.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author sytsnb@gmail.com
 * @Date 2022 2022/10/13 10:57
 */

@Getter
@Setter
@EqualsAndHashCode
@ToString(callSuper = true)
public class AdminDetails extends User {
    private Long id;

    public AdminDetails(String username, String password, boolean enabled,
                        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, true, true, true, authorities);
    }
}
