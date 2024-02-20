package com.artemnizhnyk.tasklist.service.impl;

import com.artemnizhnyk.tasklist.domain.exception.ResourceNotFoundException;
import com.artemnizhnyk.tasklist.domain.model.task.Status;
import com.artemnizhnyk.tasklist.domain.model.task.Task;
import com.artemnizhnyk.tasklist.domain.model.user.User;
import com.artemnizhnyk.tasklist.repository.TaskRepository;
import com.artemnizhnyk.tasklist.service.ImageService;
import com.artemnizhnyk.tasklist.service.TaskService;
import com.artemnizhnyk.tasklist.service.UserService;
import com.artemnizhnyk.tasklist.service.mapper.TaskImageMapper;
import com.artemnizhnyk.tasklist.service.mapper.TaskMapper;
import com.artemnizhnyk.tasklist.service.mapper.UserMapper;
import com.artemnizhnyk.tasklist.web.dto.AnswerDto;
import com.artemnizhnyk.tasklist.web.dto.TaskDto;
import com.artemnizhnyk.tasklist.web.dto.TaskImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final TaskMapper taskMapper;
    private final UserMapper userMapper;
    private final TaskImageMapper taskImageMapper;

    @Cacheable(value = "TaskService::getByIdOrThrowException", key = "#id")
    @Transactional(readOnly = true)
    @Override
    public TaskDto getByIdOrThrowException(final Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = optionalTask.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Task with id: %d, wasn't found", id)));
        return taskMapper.toDto(task);
    }

//    @Transactional(readOnly = true)
    @Override
    public List<TaskDto> getAllByUserId(final Long id) {
        List<Task> usersByUserId = taskRepository.findAllByUserId(id);
        if (usersByUserId == null || usersByUserId.isEmpty()) {
            return new ArrayList<>();
        } else {
            return taskMapper.toDto(usersByUserId);
        }
    }

    @Cacheable(value = "TaskService::getByIdOrThrowException",
            condition = "#taskDto.id!=null", key = "#taskDto.id")
    @Transactional
    @Override
    public TaskDto create(TaskDto taskDto, final Long userId) {
        if (taskDto.getStatus() == null) {
            taskDto.setStatus(Status.TODO);
        }
        Task task = taskMapper.toEntity(taskDto);
        User user = userMapper.toEntity(userService.getByIdOrThrowException(userId));
        task.setUser(user);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @CachePut(value = "TaskService::getByIdOrThrowException", key = "#taskDto.id")
    @Transactional
    @Override
    public TaskDto update(final TaskDto taskDto) {
        User taskOwner = taskRepository.findById(taskDto.getId()).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Task with id: %d, wasn't found", taskDto.getId()))
        ).getUser();
        Task taskToSave = taskMapper.toEntity(taskDto);
        taskToSave.setUser(taskOwner);
        Task updatedTask = taskRepository.save(taskToSave);
        return taskMapper.toDto(updatedTask);
    }

    @CacheEvict(value = "UserService::getByIdOrThrowException", key = "#id")
    @Transactional
    @Override
    public AnswerDto deleteById(final Long id) {
        getByIdOrThrowException(id);
        taskRepository.deleteById(id);
        return AnswerDto.makeDefault(true);
    }

    @CacheEvict(value = "UserService::getByIdOrThrowException", key = "#id")
    @Override
    public void uploadImage(final Long id, final TaskImageDto taskImageDto) {
        TaskDto taskDto = getByIdOrThrowException(id);
        String fileName = imageService.upload(taskImageDto);
        taskDto.getImages().add(fileName);
        update(taskDto);

        taskImageMapper.toEntity(taskImageDto);
    }
}
