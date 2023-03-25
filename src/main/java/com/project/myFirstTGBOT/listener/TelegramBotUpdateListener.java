package com.project.myFirstTGBOT.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.project.myFirstTGBOT.entity.Task;
import com.project.myFirstTGBOT.service.TaskService;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.project.myFirstTGBOT.constant.Constants.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class TelegramBotUpdateListener implements UpdatesListener {


    private final TelegramBot telegramBot;
    private final TaskService taskService;
    private final Pattern pattern = Pattern.compile(PATTERN_DATE_TIME_TEXT);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER_DATE_TIME);


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {
                log.info("Handles update: {}", update);
                Message message = update.message();
                Long chatId = message.chat().id();
                String text = message.text();

                if ("/start".equals(text)) {
                    sendMessage(chatId, START_MESSAGE);
                } else if (text != null) {
                    Matcher matcher = pattern.matcher(text);
                    if (matcher.find()) {
                        LocalDateTime dateTime = parseStringToDate(matcher.group(1));
                        if (Objects.isNull(dateTime)) {
                            sendMessage(chatId, NO_CORRECT_DATE_TIME);
                        } else {
                            String textMessage = matcher.group(2);
                            Task task = new Task();
                            task.setChatId(chatId);
                            task.setMessage(textMessage);
                            task.setTaskDateTime(dateTime);
                            taskService.save(task);
                            sendMessage(chatId, CORRECT_TASK + dateTime.format(formatter));
                        }
                    } else {
                        sendMessage(chatId, NO_CORRECT_MESSAGE);
                    }
                }

            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (!sendResponse.isOk()) {
            log.error("Error sending message: {}", sendResponse.description());
        }

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