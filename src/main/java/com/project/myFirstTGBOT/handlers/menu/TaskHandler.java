package com.project.myFirstTGBOT.handlers.menu;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import com.project.myFirstTGBOT.handlers.BotState;
import com.project.myFirstTGBOT.service.TaskService;
import org.springframework.stereotype.Component;

@Component
public class TaskHandler implements InputHandlerMessage {

    private final TaskService taskService;

    public TaskHandler(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public SendMessage handle(Message inputMessage) {
        return taskService.createTask(inputMessage.chat().id(), inputMessage);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.TASK;
    }


}
