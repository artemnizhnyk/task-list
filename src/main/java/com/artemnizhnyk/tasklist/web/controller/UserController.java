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
    public TaskDto createTask(@PathVariable final Long id,
                              @Validated(OnCreate.class) @RequestBody TaskDto taskDto) {
        return taskService.create(taskDto, id);
    }

    @PutMapping()
    public UserDto updateTask(@Validated(OnUpdate.class) @RequestBody UserDto taskDto) {
        return userService.updateTask(taskDto);
    }

    @DeleteMapping("/{id}")
    public AnswerDto deleteById(@PathVariable final Long id) {
        return userService.deleteById(id);
    }
}
