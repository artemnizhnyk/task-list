package com.artemnizhnyk.tasklist.web.security;

import com.artemnizhnyk.tasklist.domain.model.user.Role;
import com.artemnizhnyk.tasklist.domain.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtEntityFactory {

    public static JwtEntity create(final User user) {
        return new JwtEntity(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole())
        );
    }

    private static List<? extends GrantedAuthority> mapToGrantedAuthorities(final Role role) {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        roles.add(authority);
        return roles;
    }
}
