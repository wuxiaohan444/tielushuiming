package com.jiangkai.framework.admin.bean;

import com.jiangkai.framework.admin.common.enums.Status;
import com.jiangkai.framework.source.model.Dept;
import com.jiangkai.framework.source.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Administrator
 * @date 2019/4/28 11:24
 */
@Getter
@Setter
public class LoginUser extends User implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;
    private Dept dept;


    public LoginUser(User user, Collection<? extends GrantedAuthority> authorities) {
        super();
        BeanUtils.copyProperties(user, this);
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }

    @Override
    public int hashCode() {
        return this.getUsername().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }

    /**
     * 权限信息
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return getNo();
    }

    /**
     * 账号是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return Status.ENABLE.getCode().equals(getStatus());
    }

    /**
     * 账号是否未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return Status.ENABLE.getCode().equals(getStatus());
    }

    /**
     * 密码是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return Status.ENABLE.getCode().equals(getStatus());
    }

    /**
     * 是否激活
     */
    @Override
    public boolean isEnabled() {
        return Status.ENABLE.getCode().equals(getStatus());
    }
}
