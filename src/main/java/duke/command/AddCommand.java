package duke.command;

import duke.date.InvalidDateDukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {

    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidTaskDukeException, InvalidDateDukeException {
        String cleanedInput = input.strip().toLowerCase();
        Task t = makeTask(cleanedInput);
        String output = tasks.addTask(t);
        return output;
    }

    public Task makeTask(String cleanedInput) throws InvalidTaskDukeException, InvalidDateDukeException {
        if (cleanedInput.startsWith("todo")) {
            return makeTodo(cleanedInput);
        } else if (cleanedInput.startsWith("deadline")) {
            return makeDeadline(cleanedInput);
        } else {
            return makeEvent(cleanedInput);
        }
    }

    private Todo makeTodo(String task) throws InvalidTodoDukeException {
        try {
            String[] tokens = task.split("\\s+");
            StringBuilder description = new StringBuilder();
            for (int i = 1; i < tokens.length; i++) {
                description.append(tokens[i] + " ");
            }
            return new Todo(description.toString().strip());
        } catch (IndexOutOfBoundsException | InvalidTaskDukeException e) {
            throw new InvalidTodoDukeException("Oops! Invalid \"todo\" command. Please stick to this format:\n"
                    + "  \"todo [description]\"");
        }
    }

    private Event makeEvent(String task) throws InvalidEventDukeException, InvalidDateDukeException {
        try {
            task = task.strip();
            int indexOfEvent = task.indexOf("event");
            int indexOfAt = task.indexOf("/at");
            String description = task.substring(indexOfEvent + 5, indexOfAt).strip();
            String at = task.substring(indexOfAt + 3).strip();
            return new Event(description, at);
        } catch (IndexOutOfBoundsException | InvalidTaskDukeException e) {
            throw new InvalidEventDukeException("Oops! Invalid \"event\" command. Please stick to this format:\n"
                    + "  \"event [description] /at [time]\"");
        }
    }

    private Deadline makeDeadline(String task) throws InvalidDeadlineDukeException, InvalidDateDukeException {
        try {
            task = task.strip();
            int indexOfDeadline = task.indexOf("deadline");
            int indexOfBy = task.indexOf("/by");
            String description = task.substring(indexOfDeadline + 8, indexOfBy).strip();
            String by = task.substring(indexOfBy + 3).strip();
            return new Deadline(description, by);
        } catch (IndexOutOfBoundsException | InvalidTaskDukeException e) {
            throw new InvalidDeadlineDukeException("Oops! Invalid \"deadline\" command. Please stick to this format:\n"
                    + "  \"deadline [description] /by [time]\"");
        }
    }

    public boolean checkExit() {
        return false;
    }

}
