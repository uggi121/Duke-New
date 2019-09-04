import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
