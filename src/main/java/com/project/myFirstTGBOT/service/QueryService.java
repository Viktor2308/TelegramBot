package com.project.myFirstTGBOT.service;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

import static com.project.myFirstTGBOT.constant.Constants.QUERY_NO_TASKS;

@Service
public class QueryService {

    private final TaskRepositoryService taskRepositoryService;

    public QueryService(TaskRepositoryService taskRepositoryService) {
        this.taskRepositoryService = taskRepositoryService;
    }

    public SendMessage queryGetAllTask(CallbackQuery callbackQuery) {
        StringBuilder allTask = new StringBuilder();
        taskRepositoryService.getAllTaskByChatId(callbackQuery.message().chat().id())
                .forEach(task -> allTask
                        .append(task.getTaskDateTime())
                        .append(" ")
                        .append(task.getMessage() + "\n")
                );
        String response = QUERY_NO_TASKS;
        if(!allTask.isEmpty()){
           response = allTask.toString();
        }

        final SendMessage sendMessage = new SendMessage(callbackQuery.message().chat().id(), response);

        return sendMessage;
    }
}
