package baguette;
import java.time.LocalDateTime;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles parsing of user input and date-time formatting.
 */
public class Parser {
    /**
     * Handles errors related to invalid indexing.
     * Delegates command parsing to {@code handleCommand(String)}
     *
     * @param message The user input command to be processed.
     */
    public static void checkInput(String message) {
        try {
            handleCommand(message);
        } catch (NumberFormatException e) {
            Ui.printIntegerError();
        }
    }

    /**
     * Processes the user input and executes commands based on prefix.
     *
     * @param message The user input command to be processed.
     * @throws NumberFormatException If an invalid format is detected for indexing.
     */
    public static void handleCommand(String message) throws NumberFormatException {
        if (message.startsWith("list")) {
            Ui.printList();
        } else if (message.startsWith("delete ")) {
            int index = Integer.parseInt(message.substring(7));
            TaskList.deleteTask(index - 1);
        } else if (message.startsWith("mark ")) {
            int index = Integer.parseInt(message.substring(5));
            TaskList.toggleMarkTask(index - 1, true);
        } else if (message.startsWith("unmark ")) {
            int index = Integer.parseInt(message.substring(7));
            TaskList.toggleMarkTask(index - 1, false);
        } else if (message.startsWith("find ")) {
            TaskList.find(message.substring(5).trim());
        } else {
            TaskList.addTask(message);
        }
    }

    /**
     * Extracts the task type from the given message.
     *
     * @param message The user command.
     * @return The task type as a string.
     */
    public static String parseTaskType(String message) {
        message = message.trim();
        int index = message.indexOf(" ");
        return (index == -1) ? "" : message.substring(0, index);
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     * Defaults to current time upon encountering invalid date formats.
     *
     * @param message The date-time string in "yyyy-MM-dd HH:mm" format.
     * @return The parsed LocalDateTime object.
     */
    public static LocalDateTime parseDateTime(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm", Locale.ENGLISH);

        try {
            return LocalDateTime.parse(message, formatter);
        } catch (DateTimeParseException e) {
            System.out.println(new BaguetteException(Constants.WARN_INVALID_DATE_FORMAT));
            return LocalDateTime.now();
        }
    }

    /**
     * Converts a LocalDateTime object into a formatted string representation.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return The formatted date-time string in "dd MMM yyyy HH:mm" format.
     */
    public static String toStringDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm", Locale.ENGLISH);
        return dateTime.format(formatter);
    }

    private Parser() {}
}
