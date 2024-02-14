package com.artemnizhnyk.tasklist.web.controller;

import com.artemnizhnyk.tasklist.service.TaskService;
import com.artemnizhnyk.tasklist.service.UserService;
import com.artemnizhnyk.tasklist.web.dto.AnswerDto;
import com.artemnizhnyk.tasklist.web.dto.TaskDto;
import com.artemnizhnyk.tasklist.web.dto.UserDto;
import com.artemnizhnyk.tasklist.web.dto.validation.OnCreate;
import com.artemnizhnyk.tasklist.web.dto.validation.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Controller", description = "User API")
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    @Operation(summary = "Get UserDto by user id")
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable final Long id) {
        return userService.getByIdOrThrowException(id);
    }

    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    @Operation(summary = "Get all User tasks by user id")
    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTasksByUserId(@PathVariable final Long id) {
        return taskService.getAllByUserId(id);
    }

    @PreAuthorize("@customSecurityExpression.canAccessUser(#userId)")
    @Operation(summary = "Add Task to User")
    @PostMapping("/{id}/tasks")
    public TaskDto createTask(@PathVariable("id") final Long userId,
                              @Validated(OnCreate.class) @RequestBody TaskDto taskDto) {
        return taskService.create(taskDto, userId);
    }

    @PreAuthorize("@customSecurityExpression.canAccessUser(#userDto.id)")
    @Operation(summary = "Update User")
    @PutMapping()
    public UserDto updateTask(@Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    @Operation(summary = "Delete User by user id")
    @DeleteMapping("/{id}")
    public AnswerDto deleteById(@PathVariable final Long id) {
        return userService.deleteById(id);
    }
}
