package com.artemnizhnyk.tasklist.service;

import com.artemnizhnyk.tasklist.web.dto.TaskDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public TaskDto getById(final Long id) {
        return null;
    }

    @Override
    public List<TaskDto> getAllByUserId(final Long userId) {
        return null;
    }

    @Override
    public TaskDto update(final TaskDto taskDto) {
        return null;
    }

    @Override
    public TaskDto create(final TaskDto taskDto) {
        return null;
    }

    @Override
    public boolean delete(final Long id) {
        return false;
    }
}
