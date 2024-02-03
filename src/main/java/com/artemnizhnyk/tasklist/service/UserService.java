package com.artemnizhnyk.tasklist.service;

import com.artemnizhnyk.tasklist.web.dto.AnswerDto;
import com.artemnizhnyk.tasklist.web.dto.TaskDto;
import com.artemnizhnyk.tasklist.web.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getByIdOrThrowException(final Long id);

    UserDto getByUsername(final String username);

    UserDto update(final UserDto userDto);

    TaskDto createTask(final TaskDto taskDto, final Long userId);

    boolean isTaskOwner(final Long userId, final Long taskId);

    AnswerDto deleteById(final Long id);

    List<TaskDto> getTasksByUserId(final Long id);
}