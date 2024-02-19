package com.artemnizhnyk.tasklist.service.mapper;

import com.artemnizhnyk.tasklist.domain.model.task.Task;
import com.artemnizhnyk.tasklist.web.dto.TaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskDto> {
}
