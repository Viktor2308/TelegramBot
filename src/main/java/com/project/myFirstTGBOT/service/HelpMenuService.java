package com.project.myFirstTGBOT.service;

import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class HelpMenuService {
    public SendMessage getHelpMenuMessage(Long id, String helpMessage) {
        return new SendMessage(id, helpMessage);
    }
}
