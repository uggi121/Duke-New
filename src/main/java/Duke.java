import duke.command.Command;
import duke.command.LoadCommand;
import duke.command.SaveCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

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

    String getResponse(String input) {
        try {
            Command c = parser.parseInput(input);
            String output = c.execute(tasks, ui, storage);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
