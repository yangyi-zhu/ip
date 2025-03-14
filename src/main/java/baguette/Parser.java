package baguette;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
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

    public static String parseTaskType(String message) {
        message = message.trim();
        int index = message.indexOf(" ");
        return (index == -1) ? "" : message.substring(0, index);
    }

    public static LocalDateTime parseDateTime(String message) {
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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

    public static String toStringDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dateTime.format(formatter);
    }

    private Parser() {}
}
