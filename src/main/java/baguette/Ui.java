package baguette;

import baguette.datatypes.Task;

import java.util.ArrayList;

/**
 * Handles user interface-related operations such as displaying messages and task lists.
 */
public class Ui {
    /**
     * Prints an error message when loading tasks from storage fails.
     */
    public static void printLoadingError() {
        System.out.println(Constants.WARN_LOADING_ERROR);
    }

    /**
     * Prints the application logo.
     */
    public static void printLogo() {
        System.out.println(Constants.baguetteLogo);
    }

    /**
     * Prints the welcome message when the application starts.
     */
    public static void printWelcomeMessage() {
        System.out.println(Constants.WELCOME);
    }

    /**
     * Prints the goodbye message when the application exits.
     */
    public static void printGoodbyeMessage() {
        System.out.println(Constants.GOODBYE);
    }

    /**
     * Prints a success message when a task is successfully deleted.
     *
     * @param task The task that was deleted.
     */
    public static void printDeleteSuccess(String task) {
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
                + Constants.STATUS_DELETE + Constants.NEW_LINE + "  " + task
                + Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE);
    }

    /**
     * Prints an error message when a task deletion attempt fails.
     */
    public static void printDeleteFail() {
        System.out.println(Constants.WARN_DELETE_FAILED);
    }

    /**
     * Prints the updated status of a task after being marked or unmarked.
     *
     * @param task The task whose status was updated.
     * @param isMarked True if the task was marked as completed, false if unmarked.
     */
    public static void printMarkStatus(Task task, boolean isMarked) {
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
                + (isMarked ? Constants.STATUS_MARK : Constants.STATUS_UNMARK)
                + Constants.NEW_LINE + "  " + task + Constants.NEW_LINE
                + Constants.DIVIDER + Constants.NEW_LINE);
    }

    /**
     * Prints a success message when a task is successfully added.
     *
     * @param task The task that was added.
     * @param size The total number of tasks after adding the new task.
     */
    public static void printAddSuccess(Task task, int size) {
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
                + "The following task has been added:"
                + Constants.NEW_LINE + "  " + task + Constants.NEW_LINE
                + "You now have " + size + " tasks in the tasks."
                + Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE);
    }

    /**
     * Prints an error message when a task addition attempt fails.
     */
    public static void printAddFail() {
        System.out.println(Constants.WARN_ADD_FAILED);
    }

    /**
     * Prints the list of tasks currently stored.
     */
    public static void printList() {
        ArrayList<Task> tasks = TaskList.getList();
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE + "Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(Constants.DIVIDER + Constants.NEW_LINE);
    }

    /**
     * Prints the search results based on a keyword query.
     *
     * @param list The list of tasks that match the search criteria.
     */
    public static void printSearchResults(ArrayList<Task> list) {
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER);
        if (list.size() == 0) {
            System.out.println(Constants.STATUS_EMPTY_RESULTS);
        } else {
            System.out.println(list.size() + (list.size() == 1 ? " result" : " results")
                    + Constants.STATUS_FOUND + Constants.NEW_LINE);
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }
        System.out.println(Constants.DIVIDER + Constants.NEW_LINE);
    }

    /**
     * Prints an error message when marking/unmarking fails due to invalid indexing.
     *
     * @param isMark Whether the task was to be marked as complete or otherwise.
     */
    public static void printToggleMarkFail(boolean isMark) {
        String output = Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
                + "Failed to " + (isMark ? "mark" : "unmark") + " as completed, "
                + "index out of bounds."
                + Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE;
        System.out.println(output);
    }

    /**
     * Prints an error message when an Integer is expected but another format is detected.
     */
    public static void printIntegerError() {
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
                + Constants.WARN_INT + Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE);
    }

    public static void printAlreadyCompleted() {
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
                + Constants.WARN_COMPLETE + Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE);
    }

    public static void printIncomplete() {
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE
                + Constants.WARN_INCOMPLETE + Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE);
    }

    private Ui() {}
}
