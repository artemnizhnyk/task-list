package com.artemnizhnyk.tasklist.web.dto;

import com.artemnizhnyk.tasklist.domain.model.task.Status;
import com.artemnizhnyk.tasklist.web.dto.validation.OnCreate;
import com.artemnizhnyk.tasklist.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    @Null(message = "Id must be null", groups = OnCreate.class)
    private Long id;
    @NotNull(message = "Title must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Title length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String title;
    @Length(max = 255, message = "Title length must be less than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    private String description;
    private Status status;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expirationDate;
}
