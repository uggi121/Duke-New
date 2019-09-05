public class Serializer {

    public Task deserializeTask(String input) throws InvalidTaskDukeException, InvalidDateDukeException {
        String[] tokens = input.split(" \\| ");
        if (tokens[0].equals("T")) {
            return deserializeTodo(tokens);
        } else if (tokens[0].equals("D")) {
            return deserializeDeadline(tokens);
        } else if (tokens[0].equals("E")) {
            return deserializeEvent(tokens);
        } else {
            throw new InvalidTaskDukeException("Unable to deserialize task from text.");
        }
    }

    private Task deserializeTodo(String[] tokens) throws InvalidTaskDukeException {
        Task t = new Todo(tokens[2]);
        if (tokens[1].equals("1")) {
            t.setDone(true);
        }
        return t;
    }

    private Task deserializeEvent(String[] tokens) throws InvalidDateDukeException, InvalidTaskDukeException {
        Task t = new Event(tokens[2], tokens[3]);
        if (tokens[1].equals("1")) {
            t.setDone(true);
        }
        return t;
    }

    private Task deserializeDeadline(String[] tokens) throws InvalidDateDukeException, InvalidTaskDukeException {
        Task t = new Deadline(tokens[2], tokens[3]);
        if (tokens[1].equals("1")) {
            t.setDone(true);
        }
        return t;
    }

    public String serializeTask(Task task) throws InvalidTaskDukeException {
        String completionState = task.getIsDone() ? "1" : "0";
        String description = task.getDescription();
        if (task instanceof Todo) {
            return "T | " + completionState + " | " + description;
        } else if (task instanceof Event) {
            return "E | " + completionState + " | " + description + " | " + task.getTime();
        } else if (task instanceof Deadline) {
            return "D | " + completionState + " | " + description + " | " + task.getTime();
        } else {
            throw new InvalidTaskDukeException("Oops! Unable to serialize this task.");
        }
    }
}
