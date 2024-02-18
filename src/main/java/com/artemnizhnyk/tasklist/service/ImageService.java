package com.artemnizhnyk.tasklist.service;

import com.artemnizhnyk.tasklist.web.dto.TaskImageDto;

public interface ImageService {

    String upload(final TaskImageDto taskImageDto);
}
