import java.util.Scanner;

public class Main {
    public static final String DIVIDER = "--------------------------";

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
            System.out.println("\n" + DIVIDER);
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + list[i]);
            }
            System.out.println(DIVIDER + "\n");
        } else if (msg.startsWith("mark ")) {
            int index = Integer.parseInt(msg.substring(5));
            list[index - 1].mark();
            System.out.println("\n" + DIVIDER);
            System.out.println("Congratulations! The following task has been marked as complete:");
            System.out.println("  " + list[index - 1]);
            System.out.println(DIVIDER + "\n");
        } else if (msg.startsWith("unmark ")) {
            int index = Integer.parseInt(msg.substring(7));
            list[index - 1].unmark();
            System.out.println("\n" + DIVIDER);
            System.out.println("Got it! The following task has been marked as incomplete:");
            System.out.println("  " + list[index - 1]);
            System.out.println(DIVIDER + "\n");
        } else {
            list[count] = new Task(msg);
            count++;
            System.out.println("\n" + DIVIDER + "\n" +
                    "added: " + msg + "\n" +
                    DIVIDER + "\n");
        }
    }

    public static void main(String[] args) {
        Scanner msg = new Scanner(System.in);
        System.out.println(DIVIDER + "\n" +
                "Bonjour et bievenue! Vous pouvez m'appeler Baguette! \n" +
                "What do you think about my intro? :D \n" +
                "Anyhow, what are we thinking today? \n" +
                DIVIDER + "\n");
        while (true) {
            String input = msg.nextLine();
            if (input.equals("bye")) {
                System.out.println("\n" + DIVIDER + "\n" +
                        "Have a nice day! \n" +
                        DIVIDER);
                return;
            }
            checkInput(input);
        }
    }
}