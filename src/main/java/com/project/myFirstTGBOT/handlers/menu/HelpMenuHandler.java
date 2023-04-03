package com.project.myFirstTGBOT.handlers.menu;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import com.project.myFirstTGBOT.handlers.BotState;
import com.project.myFirstTGBOT.service.HelpMenuService;
import org.springframework.stereotype.Component;

import static com.project.myFirstTGBOT.constant.Constants.HELP_MESSAGE;

@Component
public class HelpMenuHandler implements InputHandlerMessage {

    private final HelpMenuService helpMenuService;

    public HelpMenuHandler(HelpMenuService mainMenuService) {
        this.helpMenuService = mainMenuService;
    }

    @Override
    public SendMessage handle(Message inputMessage) {
        return helpMenuService.getHelpMenuMessage(inputMessage.chat().id(), HELP_MESSAGE);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SHOW_HELP_MENU;
    }
}
