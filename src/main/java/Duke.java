import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();
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
                c.execute(tasks, ui);
                if (c.checkExit()) {
                    break;
                }
            } catch (DukeException e) {
                ui.displayOutput(e.getMessage());
            }
        }
    }

}
