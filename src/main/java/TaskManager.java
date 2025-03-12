import java.util.ArrayList;
import java.util.Scanner;

import datatypes.Deadline;
import datatypes.Event;
import datatypes.Task;
import datatypes.Todo;

public class TaskManager {
    private static final ArrayList<Task> list = new ArrayList<Task>();

    public static void printList() {
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE + "Tasks:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println(Constants.DIVIDER + Constants.NEW_LINE);
    }

    public static void deleteTask(int index) {
        if (index < 1 || index > list.size()) {
            System.out.println(Constants.WARN_DELETE_FAILED);
            return;
        }

        String task = list.get(index).toString();
        list.remove(index);

        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE +
                Constants.STATUS_DELETE + Constants.NEW_LINE + "  " + task +
                Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
        );
    }

    public static void toggleMarkTask(int index, boolean isMarked) {
        if (isMarked) {
            list.get(index).mark();
        } else {
            list.get(index).unmark();
        }

        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE +
                        (isMarked ? Constants.STATUS_MARK : Constants.STATUS_UNMARK) +
                        Constants.NEW_LINE + "  " + list.get(index) + Constants.NEW_LINE +
                        Constants.DIVIDER + Constants.NEW_LINE
        );
    }

    public static boolean checkEmptyDescription(String description) {
        if (description.isBlank()) {
            System.out.println(
                    Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE +
                            Constants.WARN_BLANK_DESCRIPTION +
                            Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
            );
            return true;
        }

        return false;
    }

    public static void addTask(String message) {
        int index;
        boolean isAddSuccess = false;
        Scanner scanner = new Scanner(System.in);

        while (!isAddSuccess) {
            if (message.startsWith("todo ")) {
                index = Constants.INDEX_TODO;
                if (!checkEmptyDescription(message.substring(index))) {
                    isAddSuccess = true;
                    list.add(new Todo(message.substring(index)));
                }
            } else if (message.startsWith("deadline ")) {
                index = Constants.INDEX_DEADLINE;
                int indexDdl = message.indexOf("ddl:");
                list.add(new Deadline(message.substring(index, indexDdl), message.substring(indexDdl + 5)));
            } else if (message.startsWith("event ")) {
                index = Constants.INDEX_EVENT;
                list.add(new Event(message.substring(index), "", ""));
            } else {
                System.out.println(Constants.WARN_ADD_FAILED);
                return;
            }

            if (!isAddSuccess) {
                message = scanner.nextLine();
            }
        }

        System.out.println(
                Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE +
                        "The following task has been added:" +
                        Constants.NEW_LINE + "  " + list.get(list.size() - 1) + Constants.NEW_LINE +
                        "You now have " + (list.size()) + " tasks in the list." +
                        Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
        );
    }

    public static void checkInput(String message) {
        if (message.equals("list")) {
            printList();
        } else if (message.startsWith("delete ")) {
            int index = Integer.parseInt(message.substring(7));
            deleteTask(index);
        } else if (message.startsWith("mark ")) {
            int index = Integer.parseInt(message.substring(5));
            toggleMarkTask(index - 1, true);
        } else if (message.startsWith("unmark ")) {
            int index = Integer.parseInt(message.substring(7));
            toggleMarkTask(index - 1, false);
        } else {
            addTask(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Baguette.printLogo();
        System.out.println(Constants.WELCOME);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(Constants.GOODBYE);
                return;
            }
            checkInput(input);
        }
    }
}