package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String listOfTasks = tasks.getListOfTasks();
        ui.displayOutput(listOfTasks);
    }

    public boolean checkExit() {
        return false;
    }
}
