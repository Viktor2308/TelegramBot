package com.project.myFirstTGBOT.service;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import com.project.myFirstTGBOT.entity.Task;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.project.myFirstTGBOT.constant.Constants.*;

@Service
public class TaskService {

    private final TaskRepositoryService taskRepositoryService;

    public TaskService(TaskRepositoryService taskRepositoryService) {
        this.taskRepositoryService = taskRepositoryService;
    }

    private final Pattern pattern = Pattern.compile(PATTERN_DATE_TIME_TEXT);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER_DATE_TIME);


    public SendMessage createTask(Long id, Message inputMessage) {
        String responseMessage;
        Matcher matcher = pattern.matcher(inputMessage.text());
        if (matcher.find()) {
            LocalDateTime dateTime = parseStringToDate(matcher.group(1));
            if (Objects.isNull(dateTime)) {
                responseMessage = NO_CORRECT_DATE_TIME;
            } else {
                String textMessage = matcher.group(2);
                Task task = new Task();
                task.setChatId(id);
                task.setMessage(textMessage);
                task.setTaskDateTime(dateTime);
                taskRepositoryService.save(task);
                responseMessage = CORRECT_TASK + dateTime.format(formatter);
            }
        } else {
            responseMessage = NO_CORRECT_MESSAGE;
        }
        final SendMessage taskServiceMessage = new SendMessage(id,responseMessage);

        return taskServiceMessage;
    }



    @Nullable
    private LocalDateTime parseStringToDate(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeException e) {
            return null;
        }
    }
}
