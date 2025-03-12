import java.util.Scanner;

public class TaskManager {
    private static int count = 0;
    private static final Task[] list = new Task[100];

    public static void printList() {
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE + "Tasks:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
        System.out.println(Constants.DIVIDER + Constants.NEW_LINE);
    }

    public static void deleteTask(int index) {
        if (index < 1 || index > count) {
            System.out.println(Constants.WARN_DELETE_FAILED);
            return;
        }

        String task = list[index].toString();
        for (int i = index - 1; i < count - 2; i++) {
            list[i] = list[i + 1];
        }
        count--;
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE +
                Constants.STATUS_DELETE + Constants.NEW_LINE + "  " + task +
                Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
        );
    }

    public static void toggleMarkTask(int index, boolean isMarked) {
        if (isMarked) {
            list[index].mark();
        } else {
            list[index].unmark();
        }

        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE +
                        (isMarked ? Constants.STATUS_MARK : Constants.STATUS_UNMARK) +
                        Constants.NEW_LINE + "  " + list[index] + Constants.NEW_LINE +
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
                    list[count] = new Todo(message.substring(index));
                }
            } else if (message.startsWith("deadline ")) {
                index = Constants.INDEX_DEADLINE;
                int indexDdl = message.indexOf("ddl:");
                list[count] = new Deadline(message.substring(index, indexDdl), message.substring(indexDdl + 5));
            } else if (message.startsWith("event ")) {
                index = Constants.INDEX_EVENT;
                list[count] = new Event(message.substring(index), "", "");
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
                        Constants.NEW_LINE + "  " + list[count] + Constants.NEW_LINE +
                        "You now have " + (count + 1) + " tasks in the list." +
                        Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
        );
        count++;
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