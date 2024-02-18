package com.artemnizhnyk.tasklist.service.impl;

import com.artemnizhnyk.tasklist.service.ImageService;
import com.artemnizhnyk.tasklist.web.dto.TaskImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String upload(TaskImageDto taskImageDto) {
        return null;
    }
}
