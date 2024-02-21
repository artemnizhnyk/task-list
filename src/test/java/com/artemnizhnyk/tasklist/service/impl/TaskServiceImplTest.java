package com.artemnizhnyk.tasklist.service.impl;

import com.artemnizhnyk.tasklist.config.TestConfig;
import com.artemnizhnyk.tasklist.domain.exception.ResourceNotFoundException;
import com.artemnizhnyk.tasklist.domain.model.task.Status;
import com.artemnizhnyk.tasklist.domain.model.task.Task;
import com.artemnizhnyk.tasklist.domain.model.user.User;
import com.artemnizhnyk.tasklist.repository.TaskRepository;
import com.artemnizhnyk.tasklist.repository.UserRepository;
import com.artemnizhnyk.tasklist.service.ImageService;
import com.artemnizhnyk.tasklist.service.UserService;
import com.artemnizhnyk.tasklist.service.mapper.TaskImageMapper;
import com.artemnizhnyk.tasklist.service.mapper.TaskMapper;
import com.artemnizhnyk.tasklist.service.mapper.UserMapper;
import com.artemnizhnyk.tasklist.web.dto.TaskDto;
import com.artemnizhnyk.tasklist.web.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;
    @MockBean
    private UserMapper userMapper;
    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void getByIdOrThrowException() {
        Long id = 1L;
        Task task = new Task();
        task.setId(id);
        when(taskRepository.findById(id))
                .thenReturn(Optional.of(task));
        TaskDto taskDto = new TaskDto();
        taskDto.setId(id);
        when(taskMapper.toDto(task))
                .thenReturn(taskDto);
        TaskDto testedTask = taskService.getByIdOrThrowException(id);
        assertNotNull(testedTask);
        assertEquals(id, testedTask.getId());
        verify(taskRepository).findById(id);
    }

    @Test
    void getByIdWithNotExistingId() {
        Long id = 1L;
        when(taskRepository.findById(id))
                .thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> taskService.getByIdOrThrowException(id));
        verify(taskRepository).findById(id);
    }

    @Test
    void getAllByUserId() {
        Long userId = 1L;
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Task task = new Task();
            task.setId((long) i);
            tasks.add(task);
        }
        when(taskRepository.findAllByUserId(userId))
                .thenReturn(tasks);
        when(taskMapper.toDto(tasks))
                .thenReturn(tasks.stream().map(e->{
                    TaskDto taskDto = new TaskDto();
                    taskDto.setId(e.getId());
                    return taskDto;
                }).toList());
        List<TaskDto> testTasks = taskService.getAllByUserId(userId);
        verify(taskRepository).findAllByUserId(userId);
        assertEquals(
                tasks.get(0).getId(),
                testTasks.get(0).getId()
        );
    }

    @Test
    void update() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setStatus(Status.DONE);

        User user = new User();
        user.setId(1L);
        Task task = new Task();
        task.setId(1L);
        task.setStatus(Status.DONE);
        task.setUser(user);

        when(taskRepository.findById(taskDto.getId()))
                .thenReturn(Optional.of(task));
        when(taskMapper.toEntity(taskDto))
                .thenReturn(task);
        when(taskRepository.save(task))
                .thenReturn(task);
        when(taskMapper.toDto(task))
                .thenReturn(taskDto);

        TaskDto testedTaskDto = taskService.update(taskDto);

        assertEquals(Status.DONE, testedTaskDto.getStatus());
        assertEquals(1L, testedTaskDto.getId());
    }

    @Test
    void create() {
        Long taskId = 1L;
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        Task task = new Task();
        task.setUser(user);
        TaskDto taskDto = new TaskDto();
        taskDto.setId(taskId);

        when(userMapper.toEntity(new UserDto()))
                .thenReturn(user);
        Task testedTask = doAnswer(invocation -> {
            Task savedTask = invocation.getArgument(0);
            savedTask.setId(taskId);
            return savedTask;
        }).when(taskRepository).save(task);
        when(taskMapper.toDto(testedTask))
                .thenReturn(taskDto);

        TaskDto testedTaskDto = taskService.create(taskDto, userId);
        verify(taskRepository).save(task);
        assertNotNull(testedTaskDto.getId());
    }

    @Test
    void updateWithNullStatus() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);

        User user = new User();
        user.setId(1L);
        Task task = new Task();
        task.setId(1L);
        task.setStatus(Status.DONE);
        task.setUser(user);

        when(taskRepository.findById(taskDto.getId()))
                .thenReturn(Optional.of(task));
        when(taskMapper.toEntity(taskDto))
                .thenReturn(task);
        when(taskRepository.save(task))
                .thenReturn(task);
        when(taskMapper.toDto(task))
                .thenReturn(taskDto);

        TaskDto testedTaskDto = taskService.update(taskDto);

        assertEquals(Status.TODO, testedTaskDto.getStatus());
        assertEquals(1L, testedTaskDto.getId());
    }
}

