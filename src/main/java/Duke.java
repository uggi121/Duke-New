import duke.command.Command;
import duke.command.LoadCommand;
import duke.command.SaveCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage();
        try {
            LoadCommand c = new LoadCommand();
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showLoadingError();
            try {
                storage.flushData();
            } catch (DukeException ex) {
                ui.displayOutput(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        ui.greetUser();
        while (ui.checkNextInput()) {
            try {
                String input = ui.readInput();
                Command c = parser.parseInput(input);
                c.execute(tasks, ui, storage);
                if (c.checkExit()) {
                    SaveCommand sc = new SaveCommand();
                    sc.execute(tasks, ui, storage);
                    break;
                }
            } catch (DukeException e) {
                ui.displayOutput(e.getMessage());
            }
        }
    }

}
