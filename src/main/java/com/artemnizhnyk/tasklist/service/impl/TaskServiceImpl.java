package com.artemnizhnyk.tasklist.service.impl;

import com.artemnizhnyk.tasklist.domain.exception.ResourceNotFoundException;
import com.artemnizhnyk.tasklist.domain.model.task.Status;
import com.artemnizhnyk.tasklist.domain.model.task.Task;
import com.artemnizhnyk.tasklist.domain.model.user.User;
import com.artemnizhnyk.tasklist.repository.TaskRepository;
import com.artemnizhnyk.tasklist.service.TaskService;
import com.artemnizhnyk.tasklist.service.UserService;
import com.artemnizhnyk.tasklist.service.mapper.TaskMapper;
import com.artemnizhnyk.tasklist.service.mapper.UserMapper;
import com.artemnizhnyk.tasklist.web.dto.AnswerDto;
import com.artemnizhnyk.tasklist.web.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public TaskDto getByIdOrThrowException(final Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = optionalTask.orElseThrow(() -> new ResourceNotFoundException(String.format("Task with id: %d, wasn't found", id)));
        return taskMapper.toDto(task);
    }

    @Override
    public List<TaskDto> getAllByUserId(final Long id) {
        List<Task> usersByUserId = taskRepository.findAllByUserId(id);
        if (usersByUserId == null || usersByUserId.isEmpty()) {
            return new ArrayList<>();
        } else {
            return taskMapper.toDto(usersByUserId);
        }
    }

    @Override
    public TaskDto create(TaskDto taskDto, final Long userId) {
        if (taskDto.getStatus() != null) {
            taskDto.setStatus(Status.TODO);
        }
        Task task = taskMapper.toEntity(taskDto);
        User user = userMapper.toEntity(userService.getByIdOrThrowException(userId));
        task.setUser(user);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskDto update(final TaskDto taskDto) {
        Task updatedTask = taskRepository.save(taskMapper.toEntity(taskDto));
        return taskMapper.toDto(updatedTask);
    }

    @Override
    public AnswerDto deleteById(final Long id) {
        getByIdOrThrowException(id);
        taskRepository.deleteById(id);
        return AnswerDto.makeDefault(true);
    }
}
