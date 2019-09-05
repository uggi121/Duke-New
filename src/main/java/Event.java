import javax.xml.validation.Validator;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) throws InvalidTaskDukeException, InvalidDateDukeException {
        super(description);
        setAt(at);
    }

    public void setAt(String at) throws InvalidDateDukeException {
        DateValidator v = new DateValidator();
        boolean isValid = v.validateDate(at, true);
        if (!isValid) {
            throw new InvalidDateDukeException("Invalid date format! Please ensure your date sticks to this format:\n"
                    + "    Deadlines : \"DD/MM/YYYY HHMM\"\n"
                    + "    Events : \"DD/MM/YYYY HHMM-HHMM\"");
        }
        this.at = at;
    }

    public String getTime() {
        return at;
    }

    @Override
    public String toString() {
        return "[Event] " + super.toString() + " (at: " + at + ")";
    }
}