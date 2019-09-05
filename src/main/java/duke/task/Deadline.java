package duke.task;

import duke.date.DateValidator;
import duke.date.InvalidDateDukeException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws InvalidTaskDukeException, InvalidDateDukeException {
        super(description);
        setBy(by);
    }

    public void setBy(String by) throws InvalidDateDukeException {
        DateValidator v = new DateValidator();
        boolean isValid = v.validateDate(by, false);
        if (!isValid) {
            throw new InvalidDateDukeException("Invalid date format! Please ensure your date sticks to this format:\n"
                    + "    Deadlines : \"DD/MM/YYYY HHMM\"\n"
                    + "    Events : \"DD/MM/YYYY HHMM-HHMM\"");
        }
        this.by = by;
    }

    public String getTime() {
        return by;
    }

    @Override
    public String toString() {
        return "[Deadline] " + super.toString() + " (by: " + by + ")";
    }
}