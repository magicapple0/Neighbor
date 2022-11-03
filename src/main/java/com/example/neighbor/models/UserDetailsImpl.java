package com.example.neighbor.models;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    @Getter
    private String password;
    @Getter
    private String username;
    private User user;

    private final boolean expired = false;
    private final boolean locked = false;
    private final boolean enabled = true;

    public UserDetailsImpl(User user) {
        this.user = user;
        password = user.getPassword();
        username = user.getLogin();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = new ArrayList<GrantedAuthority>();
        authorities.add((GrantedAuthority) () -> user.getRole().name());
        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
