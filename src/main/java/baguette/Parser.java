package baguette;

import baguette.datatypes.Deadline;
import baguette.datatypes.Event;
import baguette.datatypes.Todo;

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

    private Parser() {}
}
