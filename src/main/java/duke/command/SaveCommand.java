package duke.command;

import duke.storage.Serializer;
import duke.storage.Storage;
import duke.task.InvalidTaskDukeException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class SaveCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskDukeException {
        try {
            ArrayList<Task> taskList = tasks.getTasks();
            ArrayList<String> stringTasks = new ArrayList<>();
            Serializer serializer = new Serializer();
            for (Task t : taskList) {
                String serializedTask = serializer.serializeTask(t);
                stringTasks.add(serializedTask);
            }
            storage.saveTasks(stringTasks);
            return "Tasks saved. Bye bye!";
        } catch (IOException e) {
            return "Unable to save tasks. Please check your file path.";
        }
    }

    public boolean checkExit() {
        return false;
    }

}
