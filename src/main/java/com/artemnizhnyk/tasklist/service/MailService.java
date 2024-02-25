package com.artemnizhnyk.tasklist.service;

import com.artemnizhnyk.tasklist.domain.model.MailType;
import com.artemnizhnyk.tasklist.domain.model.user.User;
import com.artemnizhnyk.tasklist.web.dto.UserDto;

import java.util.Properties;

public interface MailService {

    void sendEmail(final UserDto userDto, final MailType type, final Properties params);
}
