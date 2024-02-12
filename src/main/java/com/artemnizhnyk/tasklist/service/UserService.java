package com.artemnizhnyk.tasklist.service;

import com.artemnizhnyk.tasklist.web.dto.AnswerDto;
import com.artemnizhnyk.tasklist.web.dto.UserDto;

public interface UserService {

    UserDto getByIdOrThrowException(final Long id);

    UserDto getByUsernameOrThrowException(final String username);

    UserDto updateUser(final UserDto userDto);

    UserDto createOrThrowException(final UserDto userDto);

    boolean isTaskOwner(final Long userId, final Long taskId);

    AnswerDto deleteById(final Long id);

}