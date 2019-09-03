import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> tasks = new ArrayList<>();

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
            } else if (input.toLowerCase().startsWith("list")) {
                displayOutput(getListOfTasks());
            } else {
                displayOutput(addTask(input));
            }
        }
    }

    private static String addTask(String task) {
        tasks.add(task);
        return "added: " + task;
    }

    private static String getListOfTasks() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append((i + 1) + ". " + tasks.get(i));
            if (i != tasks.size() - 1) {
                output.append("\n");
            }
        }
        return output.toString();
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
