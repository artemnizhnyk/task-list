package com.artemnizhnyk.tasklist.service;

import com.artemnizhnyk.tasklist.domain.model.task.Task;
import com.artemnizhnyk.tasklist.web.dto.AnswerDto;
import com.artemnizhnyk.tasklist.web.dto.TaskDto;
import com.artemnizhnyk.tasklist.web.dto.TaskImageDto;

import java.time.Duration;
import java.util.List;

public interface TaskService {

    TaskDto getByIdOrThrowException(final Long id);

    List<TaskDto> getAllByUserId(final Long id);

    TaskDto create(final TaskDto taskDto, final Long userId);

    TaskDto update(final TaskDto taskDto);

    AnswerDto deleteById(final Long id);

    void uploadImage(final Long id, final TaskImageDto taskImageDto);

    List<Task> getAllSoonTasks(Duration duration);
}
