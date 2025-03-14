package baguette;

import baguette.datatypes.Deadline;
import baguette.datatypes.Event;
import baguette.datatypes.Task;
import baguette.datatypes.Todo;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public static ArrayList<Task> getList() {
        return tasks;
    }

    public static void deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            Ui.printDeleteFail();
            return;
        }

        Ui.printDeleteSuccess(tasks.get(index).toString());
        tasks.remove(index);

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

        Ui.printMarkStatus(tasks.get(index), isMarked);

        try {
            Storage.updateFile(tasks);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addTodo(String message) {
        int index = Constants.INDEX_TODO;
        try {
            if (message.substring(index).isBlank()) {
                throw new BaguetteException(Constants.WARN_BLANK_DESCRIPTION);
            }
            tasks.add(new Todo(message.substring(index).trim()));
            return true;
        } catch (BaguetteException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean addDeadline(String message) {
        int index = Constants.INDEX_DEADLINE;
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
            return true;
        } catch (BaguetteException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean addEvent(String message) {
        int index = Constants.INDEX_EVENT;
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
            return true;
        } catch (BaguetteException e) {
            System.out.println(e);
            return false;
        }
    }

    public static void addTask(String message) {
        boolean isAddSuccess;

        switch (Parser.parseTaskType(message)) {
        case "todo":
            isAddSuccess = addTodo(message);
            break;
        case "deadline":
            isAddSuccess = addDeadline(message);
            break;
        case "event":
            isAddSuccess = addEvent(message);
            break;
        default:
            Ui.printAddFail();
            return;
        }

        if (isAddSuccess) {
            try {
                Storage.updateFile(tasks);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            Ui.printAddSuccess(tasks.get(tasks.size() - 1), tasks.size());
        }
    }

    public static void loadList() {
        ArrayList<Task> fileContent = Storage.generateList();
        tasks.addAll(fileContent);
    }

    public static void find(String keyword) {
        ArrayList<Task> list = new ArrayList<Task>();

        for (Task task : tasks) {
            if (task.getDesc().indexOf(keyword) != -1) {
                list.add(task);
            }
        }

        Ui.printSearchResults(list);
    }
}