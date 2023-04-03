package com.project.myFirstTGBOT.Cache;


import com.project.myFirstTGBOT.handlers.BotState;


public interface DataCache {

    void setUsersCurrentBotState(long userId, BotState botState);

    BotState getUsersCurrentBotState(long userId);

}
