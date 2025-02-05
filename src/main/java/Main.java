import java.util.Scanner;

public class Main {
    public static void echo(String msg) {
        if (msg.equals("bye")) return;
        System.out.println("\n" + "-------------------------- \n" +
                msg + "\n" +
                "-------------------------- \n");
        Scanner newMsg = new Scanner(System.in);
        echo(newMsg.nextLine());
    }

    public static void main(String[] args) {
        Scanner msg = new Scanner(System.in);
        System.out.println("-------------------------- \n" +
                "Bonjour et bievenue! Vous pouvez m'appeler Baguette! \n" +
                "What do you think about my intro? :D \n" +
                "Anyhow, what are we thinking today? \n" +
                "-------------------------- \n");
        echo(msg.nextLine());
        System.out.println("\n" + "-------------------------- \n" +
                "Have a nice day! \n" +
                "--------------------------");
    }
}