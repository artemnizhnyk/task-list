package com.artemnizhnyk.tasklist.service.impl;

import com.artemnizhnyk.tasklist.domain.model.MailType;
import com.artemnizhnyk.tasklist.domain.model.task.Task;
import com.artemnizhnyk.tasklist.service.MailService;
import com.artemnizhnyk.tasklist.service.Reminder;
import com.artemnizhnyk.tasklist.service.TaskService;
import com.artemnizhnyk.tasklist.service.UserService;
import com.artemnizhnyk.tasklist.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

@RequiredArgsConstructor
@Service
public class ReminderImpl implements Reminder {

    private final TaskService taskService;
    private final UserService userService;
    private final MailService mailService;
    private final Duration DURATION = Duration.ofHours(1);

    @Scheduled(cron = "0 0 * * * *")
    @Override
    public void remindForTask() {
        List<Task> tasks = taskService.getAllSoonTasks(DURATION);
        tasks.forEach(task -> {
            UserDto userDto = userService.getByIdOrThrowException(task.getUser().getId());
            Properties properties = new Properties();
            properties.setProperty("task.title", task.getTitle());
            if (task.getDescription() != null) {
                properties.setProperty("task.description", task.getDescription());
            } else  {
                properties.setProperty("task.description", "---");

            }
            mailService.sendEmail(userDto, MailType.REMINDER, properties);
        });
    }
}
