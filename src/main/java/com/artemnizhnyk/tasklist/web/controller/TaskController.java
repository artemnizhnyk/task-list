package com.artemnizhnyk.tasklist.web.controller;

import com.artemnizhnyk.tasklist.service.TaskService;
import com.artemnizhnyk.tasklist.web.dto.AnswerDto;
import com.artemnizhnyk.tasklist.web.dto.TaskDto;
import com.artemnizhnyk.tasklist.web.dto.validation.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Task Controller", description = "Task API")
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
@RestController
public class TaskController {

    private final TaskService taskService;

    @PreAuthorize("@customSecurityExpression.canAccessTask(#id)")
    @Operation(summary = "Get TaskDto by task id")
    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable final Long id) {
        return taskService.getByIdOrThrowException(id);
    }

    @PreAuthorize("@customSecurityExpression.canAccessTask(#taskDto.id)")
    @Operation(summary = "Update Task")
    @PutMapping()
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto taskDto) {
        return taskService.update(taskDto);
    }

    @PreAuthorize("@customSecurityExpression.canAccessTask(#id)")
    @Operation(summary = "Delete Task by task id")
    @DeleteMapping("/{id}")
    public AnswerDto deleteById(@PathVariable final Long id) {
        return taskService.deleteById(id);
    }
}
