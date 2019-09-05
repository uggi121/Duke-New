package duke.command;

import duke.exception.DukeException;
import duke.storage.Serializer;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LoadCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ArrayList<String> stringTasks = storage.loadTasks();
            ArrayList<Task> taskList = new ArrayList<>();
            Serializer serializer = new Serializer();
            for (String s : stringTasks) {
                Task t = serializer.deserializeTask(s);
                taskList.add(t);
            }
            tasks.addAllTasks(taskList);
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        }
    }

    public boolean checkExit() {
        return false;
    }

}
