package Baguette;

import Baguette.datatypes.Task;
import Baguette.datatypes.Todo;
import Baguette.datatypes.Deadline;
import Baguette.datatypes.Event;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private static final ArrayList<Task> tasks = new ArrayList<Task>();

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

        if (message.startsWith("todo ")) {
            index = Constants.INDEX_TODO;
            try {
                if (message.substring(index).isBlank()) {
                    throw new BaguetteException(Constants.WARN_BLANK_DESCRIPTION);
                }
                tasks.add(new Todo(message.substring(index).trim()));
                isAddSuccess = true;
            } catch (BaguetteException e) {
                System.out.println(e);
            }
        } else if (message.startsWith("deadline ")) {
            index = Constants.INDEX_DEADLINE;
            int indexDdl = message.indexOf("ddl: ");

            try {
                if (indexDdl == -1) {
                    throw new BaguetteException(Constants.WARN_MISSING_DEADLINE);
                }

                String description = message.substring(index, indexDdl).trim();
                String deadline = message.substring(indexDdl + 5).trim();

                if (description.isEmpty() || deadline.isEmpty()) {
                    throw new BaguetteException(Constants.WARN_FIELD_EMPTY);
                }

                tasks.add(new Deadline(description, deadline));
                isAddSuccess = true;
            } catch (BaguetteException e) {
                System.out.println(e);
            }
        } else if (message.startsWith("event ")) {
            index = Constants.INDEX_EVENT;
            int indexFrom = message.indexOf("from: ");
            int indexTo = message.indexOf("to: ");

            try {
                if (indexFrom == -1 || indexTo == -1 || indexFrom >= indexTo) {
                    throw new BaguetteException(Constants.WARN_MISSING_EVENT);
                }

                String description = message.substring(index, indexFrom).trim();
                String from = message.substring(indexFrom + 6, indexTo).trim();
                String to = message.substring(indexTo + 4).trim();

                if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    throw new BaguetteException(Constants.WARN_FIELD_EMPTY);
                }

                tasks.add(new Event(description, from, to));
                isAddSuccess = true;
            } catch (BaguetteException e) {
                System.out.println(e);
            }
        } else {
            System.out.println(Constants.WARN_ADD_FAILED);
            return;
        }

        if (isAddSuccess) {
            try {
                Storage.updateFile(tasks);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            System.out.println(
                    Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE +
                            "The following task has been added:" +
                            Constants.NEW_LINE + "  " + tasks.get(tasks.size() - 1) + Constants.NEW_LINE +
                            "You now have " + (tasks.size()) + " tasks in the tasks." +
                            Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
            );
        }
    }

    public static void checkInput(String message) {
        try {
            try {
                if (message.startsWith("list")) {
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
            } catch (NumberFormatException e) {
                throw new BaguetteException(Constants.WARN_INT);
            }
        } catch (BaguetteException e) {
            System.out.println(e);
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