package com.project.myFirstTGBOT.constant;


public final class Constants {
    public static final String START_MESSAGE = "Hello!\n" +
            "I can help you with planning your tasks:)\n" +
            "To add new reminder\n" +
            "send me your reminder in format:\n" +
            "01.01.2024 21:00 Text your reminder\n" +
            "Push the button to see all remainders.";
    public static final String NO_CORRECT_MESSAGE = "No correct message.";
    public static final String NO_CORRECT_DATE_TIME = "Invalid date or time format.";
    public static final String PATTERN_DATE_TIME_TEXT = "(\\d{1,2}.\\d{1,2}.\\d{4} \\d{1,2}.\\d{2})\\s+([A-zА-я\\d\\s.,!?:]+)";
    public static final String CORRECT_TASK = "I added a task to the calendar,\n I will remind you about it:\n";
    public static final String FORMATTER_DATE_TIME = "dd.MM.yyyy HH:mm";
    public static final String HELP_MESSAGE = "I can help you:)";
    public static final String QUERY_SET_REMINDER = "query set reminder";
    public static final String QUERY_SHOW_ALL_REMINDER  = "query show all reminder";
    public static final String QUERY_ERROR  = "query error";
    public static final String QUERY_NO_TASKS  = "I don't have any tasks";
}
