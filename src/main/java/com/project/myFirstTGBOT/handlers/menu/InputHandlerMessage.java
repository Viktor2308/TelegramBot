package com.project.myFirstTGBOT.handlers.menu;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import com.project.myFirstTGBOT.handlers.BotState;

public interface InputHandlerMessage {

    SendMessage handle(Message inputMessage);

    BotState getHandlerName();
}
