package Baguette;

public final class Constants {
    public static final String DIVIDER = "--------------------------";
    public static final String NEW_LINE = System.lineSeparator();

    public static final String STATUS_MARK = "Congratulations! The following task has been marked as complete:";
    public static final String STATUS_UNMARK = "Got it! The following task has been marked as incomplete:";
    public static final String STATUS_DELETE = "Got it! The following task has been deleted:";

    public static final String WARN_BLANK_DESCRIPTION = "You may forget things if the description was left blank! Try adding a message!";
    public static final String WARN_ADD_FAILED = NEW_LINE + DIVIDER + NEW_LINE +
            "The supported types and corresponding formats are as follows:" + NEW_LINE +
            "To-Dos: todo [description]" + NEW_LINE +
            "Deadlines: deadline [description] ddl: [DD-MM-YY HH:MM]" + NEW_LINE +
            "Events: event [description] from: [DD-MM-YY HH:MM] to: [DD-MM-YY HH:MM]" + NEW_LINE +
            NEW_LINE + "Examples:" + NEW_LINE +
            "todo Do the laundry" + NEW_LINE +
            "deadline CS2113 Increment L5 ddl: 14-02-25 16:00" + NEW_LINE +
            "event Josh's birthday party from: 03-03-25 15:00 to: 04-03-25 00:00" +
            NEW_LINE + DIVIDER + NEW_LINE +
            "Other commands: list | mark | unmark | delete [index]" +
            NEW_LINE + DIVIDER + NEW_LINE;;
    public static final String WARN_DELETE_FAILED = NEW_LINE + DIVIDER + NEW_LINE +
            "Failed to delete task, try again with a valid index within your task count!" +
            NEW_LINE + "Run \"list\" to see your current list of tasks." +
            NEW_LINE + DIVIDER + NEW_LINE;

    public static final String WELCOME = DIVIDER + NEW_LINE +
            "Bonjour et bievenue! Vous pouvez m'appeler Baguette.Baguette!" +
            NEW_LINE + "What do you think about my intro? :D" + NEW_LINE +
            "Anyhow, what are we thinking today?" +
            NEW_LINE + DIVIDER + NEW_LINE;
    public static final String GOODBYE = NEW_LINE + DIVIDER + NEW_LINE +
            "Have a nice day!" + NEW_LINE + DIVIDER;

    public static int INDEX_TODO = 5;
    public static int INDEX_DEADLINE = 9;
    public static int INDEX_EVENT = 6;

    public static int STORAGE_TIME_WIDTH = 3;
    public static int STORAGE_PREFIX_WIDTH = 5;

    public static String PREFIX_D = "*$#";
    public static String PREFIX_E_F = ":=;";
    public static String PREFIX_E_T = "+-+";

    private Constants() {}
}