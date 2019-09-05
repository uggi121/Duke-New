package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String listOfTasks = tasks.getListOfTasks();
        return listOfTasks;
    }

    public boolean checkExit() {
        return false;
    }
}
