package com.project.myFirstTGBOT.listener;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.project.myFirstTGBOT.service.TaskRepositoryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static com.project.myFirstTGBOT.constant.Constants.START_MESSAGE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TelegramBotUpdateListenerTest {

    @Mock
    private TelegramBot telegramBot;

    @Mock
    private TaskRepositoryService taskService;

    @InjectMocks
    private TelegramBotUpdateListener telegramBotUpdateListener;

    @Test
    void processStartTestListener() throws URISyntaxException, IOException {
        String fromJson = Files.readString(
                Path.of(TelegramBotUpdateListenerTest.class.getResource("update.json").toURI()));
        Update update = BotUtils.fromJson(fromJson.replace("%text%", "/start"), Update.class);
        SendResponse sendResponse = BotUtils.fromJson("{ \"ok\": true}", SendResponse.class);

        when(telegramBot.execute(any())).thenReturn(sendResponse);

        telegramBotUpdateListener.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(telegramBot).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();

        Assertions.assertThat(actual.getParameters().get("chat_id")).isEqualTo(update.message().chat(). id());
        Assertions.assertThat(actual.getParameters().get("text")).isEqualTo(START_MESSAGE);

    }
}