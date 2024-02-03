package com.artemnizhnyk.tasklist.web.controller;

import com.artemnizhnyk.tasklist.service.TaskService;
import com.artemnizhnyk.tasklist.web.dto.AnswerDto;
import com.artemnizhnyk.tasklist.web.dto.TaskDto;
import com.artemnizhnyk.tasklist.web.dto.validation.OnCreate;
import com.artemnizhnyk.tasklist.web.dto.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
@RestController
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable final Long id) {
        return taskService.getByIdOrThrowException(id);
    }

    @PutMapping()
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto taskDto) {
        return taskService.update(taskDto);
    }

    @DeleteMapping("/{id}")
    public AnswerDto deleteById(@PathVariable final Long id) {
        return taskService.deleteById(id);
    }
}
