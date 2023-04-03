package com.project.myFirstTGBOT.handlers;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import com.project.myFirstTGBOT.handlers.menu.InputHandlerMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {

    private final Map<BotState, InputHandlerMessage> messageHandlers = new HashMap<>();

    public BotStateContext(List<InputHandlerMessage> messageHandlers) {
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
    }

    public SendMessage processInputMessage(BotState currentState, Message message) {
        InputHandlerMessage currentMessageHandler = findMessageHandler(currentState);
        return currentMessageHandler.handle(message);
    }

    private InputHandlerMessage findMessageHandler(BotState currentState) {
        if (isFilingTaskState(currentState)) {
            return messageHandlers.get(BotState.TASK);
        }

        return messageHandlers.get(currentState);
    }

    private boolean isFilingTaskState(BotState currentState) {
        switch (currentState) {
            case CREATE_REMINDER:
                return true;
            default:
                return false;
        }
    }

}
