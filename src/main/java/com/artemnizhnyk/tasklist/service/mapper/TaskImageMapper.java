package com.artemnizhnyk.tasklist.service.mapper;

import com.artemnizhnyk.tasklist.domain.model.task.TaskImage;
import com.artemnizhnyk.tasklist.web.dto.TaskImageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskImageMapper extends Mappable<TaskImage, TaskImageDto> {
}
