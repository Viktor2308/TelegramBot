package com.project.myFirstTGBOT.handlers;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.project.myFirstTGBOT.Cache.UserDataCache;
import com.project.myFirstTGBOT.handlers.callbackQuery.MainCallbackQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MainHandler {

    private final UserDataCache userDataCache;
    private final BotStateContext botStateContext;
    private final MainCallbackQuery mainCallbackQuery;

    public MainHandler(UserDataCache userDataCache, BotStateContext botStateContext, MainCallbackQuery mainCallbackQuery) {
        this.userDataCache = userDataCache;
        this.botStateContext = botStateContext;
        this.mainCallbackQuery = mainCallbackQuery;
    }

    public SendMessage handleUpdate(Update update) {

        SendMessage replyMessage = null;

        if (update.callbackQuery() != null) {
            log.info("New callbackQuery from User: {} with data: {}"
                    , update.callbackQuery().from().username()
                    , update.callbackQuery().data());
            replyMessage = mainCallbackQuery.processCallbackQuery(update.callbackQuery());

            return replyMessage;
     }

        Message message = update.message();
        if (message != null && message.text() != null) {
            log.info("New message from User:{}, chatId: {},  with text: {}"
                    , message.from().username()
                    , message.chat().id(), message.text());
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }


    public SendMessage handleInputMessage(Message message) {
        String inputText = message.text();
        long userId = message.chat().id();
        BotState botState;
        SendMessage replyMessage;

        switch (inputText) {
            case "/start":
                botState = BotState.SHOW_MAIN_MENU;
                break;
            case "/help":
                botState = BotState.SHOW_HELP_MENU;
                break;

            default:
                botState = BotState.CREATE_REMINDER;
                break;
        }

        userDataCache.setUsersCurrentBotState(userId, botState);

        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }

}
