package Baguette;

import Baguette.datatypes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private static final ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String filePath = "./data/tasks.txt";

    public static void printList() {
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE + "Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(Constants.DIVIDER + Constants.NEW_LINE);
    }

    public static void deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println(Constants.WARN_DELETE_FAILED);
            return;
        }

        String task = tasks.get(index).toString();
        tasks.remove(index);

        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE +
                Constants.STATUS_DELETE + Constants.NEW_LINE + "  " + task +
                Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
        );

        try {
            Storage.updateFile(tasks);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void toggleMarkTask(int index, boolean isMarked) {
        if (isMarked) {
            tasks.get(index).mark();
        } else {
            tasks.get(index).unmark();
        }

        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE +
                        (isMarked ? Constants.STATUS_MARK : Constants.STATUS_UNMARK) +
                        Constants.NEW_LINE + "  " + tasks.get(index) + Constants.NEW_LINE +
                        Constants.DIVIDER + Constants.NEW_LINE
        );

        try {
            Storage.updateFile(tasks);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
                    tasks.add(new Todo(message.substring(index)));
                }
            } else if (message.startsWith("deadline ")) {
                index = Constants.INDEX_DEADLINE;
                int indexDdl = message.indexOf("ddl: ");
                isAddSuccess = true;
                tasks.add(new Deadline(message.substring(index, indexDdl), message.substring(indexDdl + 5)));
            } else if (message.startsWith("event ")) {
                index = Constants.INDEX_EVENT;
                isAddSuccess = true;
                tasks.add(new Event(message.substring(index), " ", " "));
            } else {
                System.out.println(Constants.WARN_ADD_FAILED);
                return;
            }

            if (!isAddSuccess) {
                message = scanner.nextLine();
            }

            try {
                Storage.updateFile(tasks);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(
                Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE +
                        "The following task has been added:" +
                        Constants.NEW_LINE + "  " + tasks.get(tasks.size() - 1) + Constants.NEW_LINE +
                        "You now have " + (tasks.size()) + " tasks in the tasks." +
                        Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
        );
    }

    public static void checkInput(String message) {
        if (message.equals("list")) {
            printList();
        } else if (message.startsWith("delete ")) {
            int index = Integer.parseInt(message.substring(7));
            deleteTask(index - 1);
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

    public static void loadList() {
        ArrayList<Task> fileContent = Storage.generateList();
        tasks.addAll(fileContent);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BaguetteLogo.printLogo();
        System.out.println(Constants.WELCOME);
        loadList();
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