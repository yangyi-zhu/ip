import java.util.Scanner;

public class Main {
    public static final String DIVIDER = "--------------------------";
    public static final String NL = System.lineSeparator(); // New Line

    private static int count = 0;
    private static final Task[] list = new Task[100];

    public static void checkInput(String msg) {
        boolean repeat;
        do {
            repeat = false;
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
                int index;

                if (msg.startsWith("todo ")) {
                    index = 5;
                    if (msg.substring(index).isBlank()) {
                        System.out.println(
                                NL + DIVIDER + NL +
                                        "You may forget things if the description was left blank! Try adding a message!" +
                                        NL + DIVIDER + NL
                        );
                        repeat = true;
                        continue;
                    }
                    list[count] = new Todo(msg.substring(index));
                } else if (msg.startsWith("deadline ")) {
                    index = 9;
                    list[count] = new Deadline(msg.substring(index), "");
                } else if (msg.startsWith("event ")) {
                    index = 6;
                    list[count] = new Event(msg.substring(index), "", "");
                } else {
                    repeat = true;
                    System.out.println(
                            NL + DIVIDER + NL +
                                    "The supported types and corresponding formats are as follows:" + NL +
                                    "To-Dos: todo [description]" + NL +
                                    "Deadlines: deadline [description] ddl: [DD-MM-YY HH:MM]" + NL +
                                    "Events: event [description] from: [DD-MM-YY HH:MM] to: [DD-MM-YY HH:MM]" + NL +
                                    NL + "Examples:" + NL +
                                    "todo Do the laundry" + NL +
                                    "deadline CS2113 Increment L5 ddl: 14-02-25 16:00" + NL +
                                    "event Josh's birthday party from: 03-03-25 15:00 to: 04-03-25 00:00" +
                                    NL + DIVIDER + NL
                    );
                }
            }
        } while (repeat);

        System.out.println(
                NL + DIVIDER + NL +
                        "The following task has been added:" +
                        NL + "  " + list[count] + NL +
                        "You now have " + (count + 1) + " tasks in the list." +
                        NL + DIVIDER + NL
        );
        count++;
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