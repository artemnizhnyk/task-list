package com.artemnizhnyk.tasklist.service;

import com.artemnizhnyk.tasklist.domain.model.MailType;
import com.artemnizhnyk.tasklist.domain.model.user.User;

import java.util.Properties;

public interface MailService {

    void sendEmail(final User user, final MailType type, final Properties params);
}
