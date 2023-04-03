package com.project.myFirstTGBOT.service;

import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

import static com.project.myFirstTGBOT.constant.Constants.QUERY_SHOW_ALL_REMINDER;

@Service
public class MainMenuService {

    public SendMessage getMainMenuMessage(final long chatId, final String textMessage) {
        final Keyboard keyboard = getMainMenuKeyboard();
        final SendMessage mainMenuMessage =
                createMessageWithKeyboard(chatId, textMessage, keyboard);

        return mainMenuMessage;
    }

    private Keyboard getMainMenuKeyboard() {


        InlineKeyboardButton buttonShowAllReminder = new InlineKeyboardButton("Show all reminder");
        buttonShowAllReminder.callbackData(QUERY_SHOW_ALL_REMINDER);

        final Keyboard replyKeyboard = new InlineKeyboardMarkup(buttonShowAllReminder);

        return replyKeyboard;
    }


    private SendMessage createMessageWithKeyboard(final long chatId,
                                                  String textMessage,
                                                  final Keyboard keyboard) {
        final SendMessage sendMessage = new SendMessage(chatId, textMessage);
        if (keyboard != null) {
            sendMessage.replyMarkup(keyboard);
        }
        return sendMessage;
    }



}
