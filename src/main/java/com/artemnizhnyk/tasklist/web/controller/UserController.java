package com.artemnizhnyk.tasklist.web.controller;

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

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable final Long id) {
        return userService.getByIdOrThrowException(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTasksByUserId(@PathVariable final Long id) {
        return userService.getTasksByUserId(id);
    }

    @PostMapping("/{id}/tasks")
    public TaskDto createTask(@PathVariable final Long id,
                              @Validated(OnCreate.class) @RequestBody TaskDto taskDto) {
        return userService.createTask(taskDto, id);
    }

    @PutMapping()
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto taskDto) {
        return userService.update(taskDto);
    }

    @DeleteMapping("/{id}")
    public AnswerDto deleteById(@PathVariable final Long id) {
        return userService.deleteById(id);
    }
}
