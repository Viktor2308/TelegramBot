package com.project.myFirstTGBOT.handlers.callbackQuery;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import com.project.myFirstTGBOT.service.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import static com.project.myFirstTGBOT.constant.Constants.*;
@Slf4j
@Component
public class MainCallbackQuery {

private QueryService queryService;

    public MainCallbackQuery(QueryService queryService) {
        this.queryService = queryService;
    }

    public SendMessage processCallbackQuery(CallbackQuery callbackQuery) {
        SendMessage sendMessage;

        switch (callbackQuery.data()){

            case QUERY_SHOW_ALL_REMINDER:
                sendMessage = queryService.queryGetAllTask(callbackQuery);
                break;
            default:
                log.error("from User:{}, chatId: {},  with text: {}",
                        callbackQuery.message().from().username(),
                        callbackQuery.message().chat().id(),
                        callbackQuery.data());
                sendMessage = null;

                break;


        }

        return sendMessage;
    }

}
