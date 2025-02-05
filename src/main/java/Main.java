import java.util.Scanner;

public class Main {
    public static final String DIVIDER = "--------------------------";

    private static int count = 0;
    private static final String[] list = new String[100];

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
        switch (msg) {
        case "list":
            System.out.println("\n" + DIVIDER);
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + list[i]);
            }
            System.out.println(DIVIDER + "\n");
            break;
        default:
            list[count] = msg;
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