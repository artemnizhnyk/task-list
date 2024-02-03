package com.artemnizhnyk.tasklist.service;

import com.artemnizhnyk.tasklist.web.dto.AnswerDto;
import com.artemnizhnyk.tasklist.web.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto getByIdOrThrowException(final Long id);

    List<TaskDto> getAllByUserId(final Long userId);

    TaskDto update(final TaskDto taskDto);

    AnswerDto deleteById(final Long id);
}
