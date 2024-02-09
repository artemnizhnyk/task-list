package com.artemnizhnyk.tasklist.web.security;

import com.artemnizhnyk.tasklist.service.UserService;
import com.artemnizhnyk.tasklist.service.mapper.UserMapper;
import com.artemnizhnyk.tasklist.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserDto userDto = userService.getByUsernameOrThrowException(username);
        return JwtEntityFactory.create(userMapper.toEntity(userDto));
    }
}
