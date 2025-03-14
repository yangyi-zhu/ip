package baguette;

import java.util.Scanner;

/**
 * The main class of the Baguette application.
 */
public class Baguette {
    private static TaskList list;

    /**
     * Initializes a new Baguette instance by creating a TaskList and loading saved tasks.
     * If an error occurs while loading, an error message is displayed.
     */
    public Baguette() {
        list = new TaskList();
        try {
            list.loadList();
        } catch (Exception e) {
            Ui.printLoadingError();
        }
    }

    /**
     * Runs the main program loop, handling user input and executing commands.
     * Prints the logo and welcome message before entering the input loop.
     * Continues to process input until the user enters "bye".
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);

        Ui.printLogo();
        Ui.printWelcomeMessage();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                Ui.printGoodbyeMessage();
                return;
            }
            Parser.checkInput(input);
        }
    }

    /**
     * The main method. Creates a Baguette instance and starts the program.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Baguette().run();
    }
}
