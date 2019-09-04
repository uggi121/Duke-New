import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public String removeTaskFromList(int id) throws InvalidDeleteDukeException {
        try {
            Task t = tasks.remove(id - 1);
            return "Nice! I've removed this task from the list:\n"
                    + "  " + t.toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDeleteDukeException("Invalid \"delete\" command. Please enter a valid task ID.");
        }
    }

    public String getDoneUpdates(ArrayList<Integer> listOfTaskIds) throws IndexOutOfBoundsException {
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

    public String addTask(Task task) throws InvalidTaskDukeException {
        tasks.add(task);
        return "Nice! I've added this task to the list:\n"
                + "  " + tasks.get(tasks.size() - 1).toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }


    public String getListOfTasks() {
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

    public void addAllTasks(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    public ArrayList<Task> getTasks() {
        return (ArrayList<Task>) tasks.clone();
    }
}
