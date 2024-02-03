package com.artemnizhnyk.tasklist.service;

import com.artemnizhnyk.tasklist.web.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto getById(final Long id) {
        return null;
    }

    @Override
    public UserDto getByUsername(final String username) {
        return null;
    }

    @Override
    public UserDto update(final UserDto userDto) {
        return null;
    }

    @Override
    public UserDto create(final UserDto userDto) {
        return null;
    }

    @Override
    public boolean isTaskOwner(final Long userId, final Long taskId) {
        return false;
    }

    @Override
    public boolean delete(final Long id) {
        return false;
    }
}
