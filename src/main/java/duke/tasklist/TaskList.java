package duke.tasklist;

import duke.command.InvalidDeleteDukeException;
import duke.task.InvalidTaskDukeException;
import duke.task.Task;

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

    public String findTasks(String descriptionToMatch) {
        StringBuilder result = new StringBuilder();
        boolean taskExists = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().toLowerCase().contains(descriptionToMatch.toLowerCase())) {
                if (taskExists) {
                    result.append("\n");
                }
                result.append((i + 1) + ". " + tasks.get(i).toString());
                taskExists = true;
            }
        }
        return result.toString();
    }

    public void addAllTasks(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Task> getTasks() {
        return (ArrayList<Task>) tasks.clone();
    }
}
