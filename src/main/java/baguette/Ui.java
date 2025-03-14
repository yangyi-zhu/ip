package baguette;

import baguette.datatypes.Task;

import java.util.ArrayList;

public class Ui {
    public static void printLoadingError() {
        System.out.println(Constants.WARN_LOADING_ERROR);
    }

    public static void printLogo() {
        System.out.println(Constants.baguetteLogo);
    }

    public static void printWelcomeMessage() {
        System.out.println(Constants.WELCOME);
    }

    public static void printGoodbyeMessage() {
        System.out.println(Constants.GOODBYE);
    }

    public static void printDeleteSuccess(String task) {
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE +
                Constants.STATUS_DELETE + Constants.NEW_LINE + "  " + task +
                Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE);
    }

    public static void printDeleteFail() {
        System.out.println(Constants.WARN_DELETE_FAILED);
    }

    public static void printMarkStatus(Task task, boolean isMarked) {
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE +
                (isMarked ? Constants.STATUS_MARK : Constants.STATUS_UNMARK) +
                Constants.NEW_LINE + "  " + task + Constants.NEW_LINE +
                Constants.DIVIDER + Constants.NEW_LINE);
    }

    public static void printAddSuccess(Task task, int size) {
        System.out.println(
                Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE +
                        "The following task has been added:" +
                        Constants.NEW_LINE + "  " + task + Constants.NEW_LINE +
                        "You now have " + size + " tasks in the tasks." +
                        Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE);
    }

    public static void printAddFail() {
        System.out.println(Constants.WARN_ADD_FAILED);
    }

    public static void printList() {
        ArrayList<Task> tasks = TaskList.getList();
        System.out.println(Constants.NEW_LINE + Constants.DIVIDER + Constants.NEW_LINE + "Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(Constants.DIVIDER + Constants.NEW_LINE);
    }

    private Ui() {}
}
