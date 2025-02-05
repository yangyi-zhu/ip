import java.util.Scanner;

public class Main {
    public static final String DIVIDER = "--------------------------";
    public static final String NL = System.lineSeparator(); // New Line

    private static int count = 0;
    private static final Task[] list = new Task[100];

    /**
    public static void echo(String msg) {
        if (msg.equals("bye")) return;
        System.out.println("\n" + "-------------------------- \n" +
                msg + "\n" +
                "-------------------------- \n");
        Scanner newMsg = new Scanner(System.in);
        echo(newMsg.nextLine());
    }
     */

    public static void checkInput(String msg) {
        if (msg.equals("list")) {
            System.out.println(NL + DIVIDER + NL + "Tasks:");
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + list[i]);
            }
            System.out.println(DIVIDER + NL);
        } else if (msg.startsWith("mark ")) {
            int index = Integer.parseInt(msg.substring(5));
            list[index - 1].mark();
            System.out.println(
                NL + DIVIDER + NL +
                "Congratulations! The following task has been marked as complete:" +
                NL + "  " + list[index - 1] + NL +
                DIVIDER + NL
            );
        } else if (msg.startsWith("unmark ")) {
            int index = Integer.parseInt(msg.substring(7));
            list[index - 1].unmark();
            System.out.println(
                NL + DIVIDER + NL +
                "Got it! The following task has been marked as incomplete:" +
                NL + "  " + list[index - 1] + NL +
                DIVIDER + NL
            );
        } else {
            int type = 0; // defaults to to-do if no prefix
            int index = 0;

            if (msg.startsWith("todo ")) {
                index = 5;
            } else if (msg.startsWith("deadline ")) {
                type = 1;
                index = 9;
            } else if (msg.startsWith("event ")) {
                type = 2;
                index = 6;
            }

            list[count] = new Task(msg.substring(index), type);
            System.out.println(
                NL + DIVIDER + NL +
                "The following task has been added:" +
                NL + "  " + list[count] + NL +
                "You now have " + (count + 1) + " tasks in the list." +
                NL + DIVIDER + NL
            );
            count++;
        }
    }

    public static void main(String[] args) {
        Scanner msg = new Scanner(System.in);
        System.out.println(
            DIVIDER + NL +
            "Bonjour et bievenue! Vous pouvez m'appeler Baguette!" +
            NL + "What do you think about my intro? :D" + NL +
            "Anyhow, what are we thinking today?" +
            NL + DIVIDER + NL
        );
        while (true) {
            String input = msg.nextLine();
            if (input.equals("bye")) {
                System.out.println(
                    NL + DIVIDER + NL +
                    "Have a nice day!" +
                     NL + DIVIDER
                );
                return;
            }
            checkInput(input);
        }
    }
}