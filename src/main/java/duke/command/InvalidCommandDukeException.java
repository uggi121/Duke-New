package duke.command;

import duke.exception.DukeException;

public class InvalidCommandDukeException extends DukeException {

    public InvalidCommandDukeException(String message) {
        super(message);
    }

}
