package com.artemnizhnyk.tasklist.web.controller;

import com.artemnizhnyk.tasklist.service.TaskService;
import com.artemnizhnyk.tasklist.service.UserService;
import com.artemnizhnyk.tasklist.web.dto.AnswerDto;
import com.artemnizhnyk.tasklist.web.dto.TaskDto;
import com.artemnizhnyk.tasklist.web.dto.UserDto;
import com.artemnizhnyk.tasklist.web.dto.validation.OnCreate;
import com.artemnizhnyk.tasklist.web.dto.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable final Long id) {
        return userService.getByIdOrThrowException(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTasksByUserId(@PathVariable final Long id) {
        return taskService.getAllByUserId(id);
    }

    @PostMapping("/{id}/tasks")
    public TaskDto createTask(@PathVariable("id") final Long userId,
                              @Validated(OnCreate.class) @RequestBody TaskDto taskDto) {
        return taskService.create(taskDto, userId);
    }

    @PutMapping()
    public UserDto updateTask(@Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/{id}")
    public AnswerDto deleteById(@PathVariable final Long id) {
        return userService.deleteById(id);
    }
}
