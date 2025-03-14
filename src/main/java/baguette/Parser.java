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
     * Processes the user input and executes commands based on prefix.
     * Handles errors related to invalid input formats.
     *
     * @param message The user input command to be processed.
     */
    public static void checkInput(String message) {
        try {
            try {
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
            } catch (NumberFormatException e) {
                throw new BaguetteException(Constants.WARN_INT);
            }
        } catch (BaguetteException e) {
            System.out.println(e);
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
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm", Locale.ENGLISH);
        try {
            try {
                dateTime = LocalDateTime.parse(message, formatter);
            } catch (DateTimeParseException e) {
                throw new BaguetteException(Constants.WARN_INVALID_DATE_FORMAT);
            }
        } catch (BaguetteException e) {
            System.out.println(e);
            dateTime = LocalDateTime.now();
        }
        return dateTime;
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
