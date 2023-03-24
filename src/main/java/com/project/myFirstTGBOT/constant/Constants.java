package com.project.myFirstTGBOT.constant;


public final class Constants {
    public static final String START_MESSAGE = "Hello!\n" +
            "I can help you with planning your tasks:)\n" +
            "Send me your task in format:\n" +
            "23.03.2023 21:00 Text task";

    public static final String NO_CORRECT_MESSAGE = "No correct message.";
    public static final String NO_CORRECT_DATE_TIME = "Invalid date or time format.";
    public static final String PATTERN_DATE_TIME_TEXT = "(\\d{1,2}.\\d{1,2}.\\d{4} \\d{1,2}.\\d{2})\\s+([A-zА-я\\d\\s.,!?:]+)";
}
