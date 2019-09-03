import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<>();

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
            if (input.strip().toLowerCase().startsWith("bye")) {
                displayOutput("Bye! Hope to see you again :-)");
                break;
            } else if (input.strip().toLowerCase().startsWith("list")) {
                displayOutput(getListOfTasks());
            } else if (input.strip().toLowerCase().startsWith("done")) {
                displayOutput(markAsDone(input));
            } else {
                displayOutput(addTask(input));
            }
        }
    }

    private static String markAsDone(String input) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(input);
        ArrayList<Integer> listOfTaskIds = new ArrayList<>();
        while (m.find()) {
            int taskId = Integer.parseInt(m.group());
            listOfTaskIds.add(taskId);
        }
        return getDoneUpdates(listOfTaskIds);
    }

    private static String getDoneUpdates(ArrayList<Integer> listOfTaskIds) {
        StringBuilder finalOutput = listOfTaskIds.size() == 1
                ? new StringBuilder("Nice! I've marked this task as done:\n")
                : new StringBuilder("Nice! I've marked these tasks as done:\n");
        for (int id : listOfTaskIds) {
            tasks.get(id - 1).setDone(true);
            finalOutput.append("  " + tasks.get(id - 1).toString() + "\n");
        }
        return finalOutput.toString();
    }

    private static String addTask(String task) {
        tasks.add(new Task(task));
        return "added: " + tasks.get(tasks.size() - 1).getDescription();
    }

    private static String getListOfTasks() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append((i + 1) + ". " + tasks.get(i));
            if (i != tasks.size() - 1) {
                output.append("\n");
            }
        }
        String finalOutput = output.toString();
        return finalOutput.isBlank()
                ? "Oops! The list is empty."
                : finalOutput;
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
