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
        for (int i = 0; i < listOfTaskIds.size(); i++) {
            tasks.get(listOfTaskIds.get(i) - 1).setDone(true);
            finalOutput.append("  " + tasks.get(listOfTaskIds.get(i) - 1).toString());
            if (i != listOfTaskIds.size() - 1) {
                finalOutput.append("\n");
            }
        }
        return finalOutput.toString();
    }

    private static String addTask(String task) {
        String taskType = task.split("\\s+")[0];
        if (taskType.equalsIgnoreCase("todo")) {
            addTodo(task);
        } else if (taskType.equalsIgnoreCase("event")) {
            addEvent(task);
        } else {
            addDeadline(task);
        }
        return "Nice! I've added this task to the list:\n"
                + "  " + tasks.get(tasks.size() - 1).toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    private static void addTodo(String task) {
        String[] tokens = task.split("\\s+");
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            description.append(tokens[i] + " ");
        }
        tasks.add(new Todo(description.toString().strip()));
    }

    private static void addEvent(String task) {
        task = task.strip();
        int indexOfEvent = task.indexOf("event");
        int indexOfAt = task.indexOf("/at");
        String description = task.substring(indexOfEvent + 5, indexOfAt).strip();
        String at = task.substring(indexOfAt + 3).strip();
        tasks.add(new Event(description, at));
    }

    private static void addDeadline(String task) {
        task = task.strip();
        int indexOfDeadline = task.indexOf("deadline");
        int indexOfBy = task.indexOf("/by");
        String description = task.substring(indexOfDeadline + 8, indexOfBy).strip();
        String by = task.substring(indexOfBy + 3).strip();
        tasks.add(new Deadline(description, by));
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
