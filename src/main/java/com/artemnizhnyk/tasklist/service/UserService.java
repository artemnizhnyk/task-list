package com.artemnizhnyk.tasklist.service;

import com.artemnizhnyk.tasklist.web.dto.UserDto;

public interface UserService {

    UserDto getById(final Long id);

    UserDto getByUsername(final String username);

    UserDto update(final UserDto userDto);

    UserDto create(final UserDto userDto);

    boolean isTaskOwner(final Long userId, final Long taskId);

    boolean delete(final Long id);
}