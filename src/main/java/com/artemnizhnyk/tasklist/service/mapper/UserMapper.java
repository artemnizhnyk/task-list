package com.artemnizhnyk.tasklist.service.mapper;

import com.artemnizhnyk.tasklist.domain.model.user.User;
import com.artemnizhnyk.tasklist.web.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {
}
