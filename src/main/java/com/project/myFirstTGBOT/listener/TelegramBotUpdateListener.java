package com.project.myFirstTGBOT.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.project.myFirstTGBOT.handlers.MainHandler;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;



import java.util.List;



@Slf4j
@Component
public class TelegramBotUpdateListener implements UpdatesListener {

    private final TelegramBot telegramBot;
    private final MainHandler mainHandler;

    public TelegramBotUpdateListener(TelegramBot telegramBot, MainHandler mainHandler) {
        this.telegramBot = telegramBot;
        this.mainHandler = mainHandler;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {

               telegramBot.execute(mainHandler.handleUpdate(update));

            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

//        private void sendStartPhoto(Long chatId){
//        try {
//            byte[] photo = Files.readAllBytes(
//                    Paths.get(TelegramBotUpdateListener.class
//                            .getResource("/telegram-cat.jpg").toURI()));
//            SendPhoto sendPhoto = new SendPhoto(chatId, photo);
//            SendResponse sendResponse = telegramBot.execute(sendPhoto);
//            if (!sendResponse.isOk()) {
//                log.error("Error sending photo: {}", sendResponse.description());
//            }
//
//        } catch (IOException | URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }

}