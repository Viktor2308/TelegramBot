package com.project.myFirstTGBOT.timer;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.project.myFirstTGBOT.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Controller
public class TaskTimer {

    private final TaskRepository taskRepository;
    private final TelegramBot telegramBot;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void task() {
        taskRepository.findAllByTaskDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .forEach(task -> {
                    telegramBot.execute(
                            new SendMessage(task.getChatId(), task.getMessage()));
                    taskRepository.delete(task);
                });

    }

}
