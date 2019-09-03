import java.util.Scanner;

public class Duke {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| |_| | | | | |/ / _ \\\n"
                + "|  _ _/ |_| |   <  __/\n"
                + "|_|   \\__,_|_|\\_\\___|\n";
        displayOutput("Hello from\n" + logo);
        run();
    }

    private static void run() {
        while (checkNextInput()) {
            String input = readInput();
            if (input.toLowerCase().startsWith("bye")) {
                displayOutput("Bye! Hope to see you again :-)");
                break;
            }
            displayOutput(input);
        }
    }

    private static String readInput() {
        return sc.nextLine();
    }

    private static boolean checkNextInput() {
        return sc.hasNext();
    }

    private static void displayOutput(String output) {
        String bound = "     ____________________________________________________________\n     ";
        String intermediate = output.replace("\n", "\n     ");
        String finalOutput = bound + intermediate + "\n" + bound;
        System.out.println(finalOutput);
    }
}
