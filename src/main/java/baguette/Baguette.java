package baguette;

import java.util.Scanner;

public class Baguette {
    private static TaskList list;

    public Baguette() {
        list = new TaskList();
        try {
            list.loadList();
        } catch (Exception e) {
            Ui.printLoadingError();
        }
    }

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

    public static void main(String[] args) {
        new Baguette().run();
    }
}
