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

    public String addTask(String task) throws InvalidTaskDukeException {
        String taskType = task.split("\\s+")[0];
        if (taskType.equalsIgnoreCase("todo")) {
            addTodo(task);
        } else if (taskType.equalsIgnoreCase("event")) {
            addEvent(task);
        } else if (taskType.equalsIgnoreCase("deadline")) {
            addDeadline(task);
        }
        return "Nice! I've added this task to the list:\n"
                + "  " + tasks.get(tasks.size() - 1).toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    private void addTodo(String task) throws InvalidTodoDukeException {
        try {
            String[] tokens = task.split("\\s+");
            StringBuilder description = new StringBuilder();
            for (int i = 1; i < tokens.length; i++) {
                description.append(tokens[i] + " ");
            }
            tasks.add(new Todo(description.toString().strip()));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTodoDukeException("Oops! Invalid \"todo\" command. Please stick to this format:\n"
                    + "  \"todo [description]\"");
        }
    }

    private void addEvent(String task) throws InvalidEventDukeException {
        try {
            task = task.strip();
            int indexOfEvent = task.indexOf("event");
            int indexOfAt = task.indexOf("/at");
            String description = task.substring(indexOfEvent + 5, indexOfAt).strip();
            String at = task.substring(indexOfAt + 3).strip();
            tasks.add(new Event(description, at));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidEventDukeException("Oops! Invalid \"event\" command. Please stick to this format:\n"
                    + "  \"event [description] /at [time]\"");
        }
    }

    private void addDeadline(String task) throws InvalidDeadlineDukeException {
        try {
            task = task.strip();
            int indexOfDeadline = task.indexOf("deadline");
            int indexOfBy = task.indexOf("/by");
            String description = task.substring(indexOfDeadline + 8, indexOfBy).strip();
            String by = task.substring(indexOfBy + 3).strip();
            tasks.add(new Deadline(description, by));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDeadlineDukeException("Oops! Invalid \"deadline\" command. Please stick to this format:\n"
                    + "  \"deadline [description] /by [time]\"");
        }
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
}
