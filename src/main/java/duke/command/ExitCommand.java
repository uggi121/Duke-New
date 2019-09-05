package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye! See you again :-)";
    }

    public boolean checkExit() {
        return true;
    }
}
