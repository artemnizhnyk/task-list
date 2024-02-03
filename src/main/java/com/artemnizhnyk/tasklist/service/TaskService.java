package com.artemnizhnyk.tasklist.service;

import com.artemnizhnyk.tasklist.web.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto getById(final Long id);

    List<TaskDto> getAllByUserId(final Long userId);

    TaskDto update(final TaskDto taskDto);

    TaskDto create(final TaskDto taskDto);

    boolean delete(final Long id);
}
