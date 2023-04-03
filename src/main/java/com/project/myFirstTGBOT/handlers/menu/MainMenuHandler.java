package com.project.myFirstTGBOT.handlers.menu;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import com.project.myFirstTGBOT.handlers.BotState;
import com.project.myFirstTGBOT.service.MainMenuService;
import org.springframework.stereotype.Component;

import static com.project.myFirstTGBOT.constant.Constants.START_MESSAGE;

@Component
public class MainMenuHandler implements InputHandlerMessage {

    private final MainMenuService mainMenuService;

    public MainMenuHandler(MainMenuService mainMenuService) {
        this.mainMenuService = mainMenuService;
    }

    @Override
    public SendMessage handle(Message message) {
        return mainMenuService.getMainMenuMessage(message.chat().id(), START_MESSAGE);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_MAIN_MENU;
    }


}
