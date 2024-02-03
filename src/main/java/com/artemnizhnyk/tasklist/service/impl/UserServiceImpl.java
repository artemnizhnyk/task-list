package com.artemnizhnyk.tasklist.service.impl;

import com.artemnizhnyk.tasklist.domain.exception.ResourceNotFoundException;
import com.artemnizhnyk.tasklist.domain.model.task.Task;
import com.artemnizhnyk.tasklist.domain.model.user.User;
import com.artemnizhnyk.tasklist.repository.TaskRepository;
import com.artemnizhnyk.tasklist.repository.UserRepository;
import com.artemnizhnyk.tasklist.service.UserService;
import com.artemnizhnyk.tasklist.service.mapper.TaskMapper;
import com.artemnizhnyk.tasklist.service.mapper.UserMapper;
import com.artemnizhnyk.tasklist.web.dto.AnswerDto;
import com.artemnizhnyk.tasklist.web.dto.TaskDto;
import com.artemnizhnyk.tasklist.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @Override
    public UserDto getByIdOrThrowException(final Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new ResourceNotFoundException(String.format("Task with id: %d, wasn't found", id)));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getByUsername(final String username) {
        return null;
    }

    @Override
    public UserDto update(final UserDto userDto) {
        User updatedUser = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(updatedUser);
    }

    @Override
    public TaskDto createTask(final TaskDto taskDto, final Long userId) {
        Task savedTask = taskRepository.save(taskMapper.toEntity(taskDto));
        Optional<User> userById = userRepository.findById(userId);
        savedTask.setUser(userById.orElseThrow(()->
                new ResourceNotFoundException(String.format("Task with id: %d, wasn't found", userId))));
        return taskMapper.toDto(savedTask);
    }

    @Override
    public boolean isTaskOwner(final Long userId, final Long taskId) {
        return false;
    }

    @Override
    public AnswerDto deleteById(final Long id) {
        getByIdOrThrowException(id);
        userRepository.deleteById(id);
        return AnswerDto.makeDefault(true);
    }

    @Override
    public List<TaskDto> getTasksByUserId(final Long id) {
        List<Task> usersByUserId = taskRepository.findAllByUserId(id);
        if (usersByUserId == null || usersByUserId.isEmpty()) {
            return new ArrayList<>();
        } else {
            return taskMapper.toDto(usersByUserId);
        }

    }
}
